package com.mamunsproject.youtubekids.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamunsproject.food_recipe_stevdza.observeOnce
import com.mamunsproject.youtubekids.Resource_
import com.mamunsproject.youtubekids.adapter.VideoAdapter
import com.mamunsproject.youtubekids.databinding.FragmentHomeBinding
import com.mamunsproject.youtubekids.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var videoAdapter: VideoAdapter
    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.IO + job)
        uiScope.launch(Dispatchers.IO) {

            // viewModel.getHomeKeyFromFirestore()
            Log.d("THREADDDSD", "HomeFragment: ${Thread.currentThread().name} ")
        }

        Log.d("THREADDDSD", "InHomeFragment: 4")


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        prepareCategoriesRecyclerView()
        setHasOptionsMenu(true)

        readDatabase()


        return binding.root;
    }


    private fun requestApiData() {

        viewModel.getApiKEY()
        viewModel.videoViewModel.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource_.Success -> {
                    hideProgressBar()
                    response.data?.let { videoResponse ->

                        videoAdapter.setData(videoResponse)

                        Log.d("RecipesFragment", "requestApiData Called")

                    }
                }
                is Resource_.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        loadDataFromCache()

                        Log.e("TAG", "An Error occurred $message")
                    }
                }
                is Resource_.Loading ->
                    showProgressBar()
            }

        })


    }

    private fun readDatabase() {


        lifecycleScope.launch {
            /** Instead of calling Observe , we calling ObserveOnce Function which we created in MyExtension
             * Because when the application run for the first time (readDatabase & requestApiData) call both
             * to get read from this type of bug this MyExtensionFunction file created
             */
            viewModel.readVideoRequest.observeOnce(viewLifecycleOwner, { database ->

                /// If database is not then Set Data from ROOM DB else Request new API*/

                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "Calling Local Database Call")

                    videoAdapter.setData(database[0].video_)
                    hideProgressBar()
                } else {
                    requestApiData()

                    Log.d("RecipesFragment", "Requesting Api")

                }
            })
        }

    }

    private fun loadDataFromCache() {

        lifecycleScope.launch {
            viewModel.readVideoRequest.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    videoAdapter.setData(database[0].video_)
                    Log.d("RecipesFragment", "loadDataFromCache Call ")

                }
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        /** By this we can safe from Memory Leaks*/
        // _binding = null
    }

    private fun hideProgressBar() {
        binding.homePB.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.homePB.visibility = View.VISIBLE
    }


    private fun prepareCategoriesRecyclerView() {

        videoAdapter = VideoAdapter()

        binding.homeRC.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = videoAdapter
        }
    }
}
