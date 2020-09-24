package  com.a.p.views.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.a.p.R
import com.a.p.BR
import com.a.p.mvvm.viewmodel.List2ViewModel
import com.a.p.views.CommonMultiItem

class List2Adapter(data: List<CommonMultiItem<List2ViewModel.DataModel>>) :
    BaseMultiItemQuickAdapter<CommonMultiItem<List2ViewModel.DataModel>, List2Adapter.DataBindingViewHolder>(
        data
    ) {


    init {
        addItemType(CommonMultiItem.ITEM_HEADER, R.layout.fragment_header_list2)
        addItemType(CommonMultiItem.ITEM_ONE, R.layout.fragment_item_list2)
    }

    override fun convert(
        helper: DataBindingViewHolder,
        item: CommonMultiItem<List2ViewModel.DataModel>
    ) {
        val binding = helper.binding
        binding.setVariable(BR.item, item.content)
        binding.executePendingBindings()


        when (helper.itemViewType) {
            CommonMultiItem.ITEM_HEADER -> helper.setText(R.id.iv_header, item.content?.letter)
            CommonMultiItem.ITEM_ONE -> when (helper.layoutPosition % 2) {
                0 -> helper.setText(R.id.tv_letter, "one ${item.content}")
                1 -> helper.setText(R.id.tv_letter, "two ${item.content}")
            }
        }
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    inner class DataBindingViewHolder(view: View) : BaseViewHolder(view) {

        val binding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }


}

