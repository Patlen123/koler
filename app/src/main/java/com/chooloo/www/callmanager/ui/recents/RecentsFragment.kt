package com.chooloo.www.callmanager.ui.recents

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chooloo.www.callmanager.adapter.RecentsAdapter
import com.chooloo.www.callmanager.entity.Recent
import com.chooloo.www.callmanager.livedata.RecentsLiveData
import com.chooloo.www.callmanager.ui.list.ListFragment
import com.chooloo.www.callmanager.viewmodel.data.DataViewModel
import com.chooloo.www.callmanager.viewmodel.data.DataViewModelFactory

class RecentsFragment : ListFragment<RecentsAdapter>(), RecentsMvpView {
    private lateinit var _presenter: RecentsPresenter<RecentsMvpView>
    private lateinit var _recentsLiveData: RecentsLiveData

    companion object {
        @JvmStatic
        fun newInstance(): RecentsFragment {
            return RecentsFragment()
        }
    }

    override fun onGetAdapter(): RecentsAdapter {
        return RecentsAdapter(_activity).apply {
            setOnRecentItemClickListener(object : RecentsAdapter.OnRecentItemClickListener {
                override fun onRecentItemClick(recent: Recent) {
                    _presenter.onRecentItemClick(recent)
                }
            })
            setOnRecentItemLongClickListener(object : RecentsAdapter.OnRecentItemLongClickListener {
                override fun onRecentItemLongClick(recent: Recent): Boolean {
                    _presenter.onRecentItemLongClick(recent)
                    return true
                }
            })
        }
    }

    override fun onSetup() {
        super.onSetup()

        _presenter = RecentsPresenter()
        _presenter.attach(this)

        _recentsLiveData = ViewModelProvider(this, DataViewModelFactory(_activity)).get(DataViewModel::class.java).recents
        _recentsLiveData.observe(viewLifecycleOwner, Observer { recents -> adapter.updateRecents(recents) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _presenter.detach()
    }

    override fun openRecent(recent: Recent) {
        // TODO implement
    }
}