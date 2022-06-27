package com.example.movieclone.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieclone.databinding.FragmentSearchBinding
import com.example.movieclone.ui.adapters.ShowAllMoviesAdapter
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

 companion object{
     const val SearchFragmentTag = "SearchFragment"
 }

    private val viewmodel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding  = FragmentSearchBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        val adapter = ShowAllMoviesAdapter()

        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                adapter.loadStateFlow.collect{
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
        binding.searchItemView.getQueryTextChangeStateFlow(binding.searchItemView.query)
            .debounce(300)
            .filter { query ->
                if (query.isEmpty()) {
                    adapter.submitData(PagingData.empty())
                    return@filter false
                } else {
                    return@filter true
                }
            }
            .distinctUntilChanged()
            .flatMapLatest { query ->
               viewmodel.getSearchMovies(query)
            }
            .flowOn(Dispatchers.Main)
            .collectLatest { result ->
                adapter.submitData(result)
            }
            }
        }
        return  binding.root
    }
}

fun SearchView.getQueryTextChangeStateFlow(searchVal : CharSequence): StateFlow<String> {

    val mQuery = searchVal.toString()
    val query = MutableStateFlow(mQuery)

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}
