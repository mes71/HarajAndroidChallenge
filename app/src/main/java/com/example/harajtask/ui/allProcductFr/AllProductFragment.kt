package com.example.harajtask.ui.allProcductFr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.harajtask.R
import com.example.harajtask.data.adapter.MainAdapter
import com.example.harajtask.data.adapter.MainAdpterHelper
import com.example.harajtask.databinding.AllProductFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllProductFragment : Fragment(), MainAdpterHelper {


    private val viewModel: AllProductViewModel by viewModels()
    private var _binding: AllProductFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adpater: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllProductFragmentBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )

        viewModel.getAllproducts()
        adpater = MainAdapter()
        adpater.mainAdpterHelper = this

        binding.mainRecycler.adapter = adpater
        binding.mainRecycler.hasFixedSize()



        observeViewModel()




        return binding.root
    }

    private fun observeViewModel() {

        viewModel.listProducts.observe(requireActivity(), {
            adpater.setData(it)
        })


    }

    override fun onClickItem(id: Int) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_allProductFragment_to_productFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}