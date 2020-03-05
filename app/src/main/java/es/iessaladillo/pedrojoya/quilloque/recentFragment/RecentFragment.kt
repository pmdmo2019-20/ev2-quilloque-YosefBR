package es.iessaladillo.pedrojoya.quilloque.recentFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.RecentCalls
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.stroop.invisibleUnless
import kotlinx.android.synthetic.main.contacts_fragment.emptyView
import kotlinx.android.synthetic.main.recent_fragment.*

class RecentFragment : Fragment(R.layout.recent_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(TlfDatabase.getInstance(requireContext()).contactDao, TlfDatabase.getInstance(requireContext()).callDao),
            requireActivity().application
        )
    }

    private val listAdapter: RecentFragmentAdapter = RecentFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            it.setOnItemClickListener {

            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeRecentCalls()
    }

    private fun setupViews(){
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstCalls.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun observeRecentCalls() {
        sharedViewModel.recentCalls.observe(this) {
            showRecentCalls(it)
        }
    }

    private fun showRecentCalls(newList: List<RecentCalls>) {
        lstCalls.post {
            listAdapter.submitList(newList)
            emptyView.invisibleUnless(newList.isEmpty())
        }
    }
}
