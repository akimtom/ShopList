package com.example.shoplist.Prisentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.Domain.ShoppingItem
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment(
    private val screenMod:String = EXTRA_UNKNOWN,
    private val shopItemId: Int = ShoppingItem.UNDEFINE_ID)  : Fragment() {
    private lateinit var viewModel: ShoppingItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var editName: EditText
    private lateinit var editCount: EditText
    private lateinit var buttonSave: Button
    private lateinit var viewFragment: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewFragment = inflater.inflate(R.layout.fragment_shop_item, container, false)
        return viewFragment
    }


    fun newIntentChangeItem(context: Context, id: Int): Intent {
        val intent = Intent(context, ShoppingItemActivity::class.java)

        intent.putExtra(EXTRA_MODE, EXTRA_CHANGE)
        intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
        return intent
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShoppingItemViewModel::class.java]
        parseParam()
        initViews()
        when (screenMod) {
            EXTRA_CHANGE -> launchChangeMod()
            EXTRA_ADD -> launchAddMod()
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner)
        {
            if (it) {
                tilCount.error = "Wrong numeric format"
            } else
                tilCount.error = null
        }
        viewModel.errorInputName.observe(viewLifecycleOwner)
        {
            if (it) {
                tilName.error = "Wrong text format"
            } else
                tilName.error = null
        }
        viewModel.isItClose.observe(viewLifecycleOwner)
        {
            activity?.onBackPressed()
        }
    }


    private fun launchChangeMod() {


        viewModel.getShopItem(shopItemId)

        editName.addTextChangedListener ( object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorName()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        } )
        editCount.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        viewModel.shopItemLd.observe(viewLifecycleOwner) {

            editName.setText(it.name)
            editCount.setText(it.count.toString())

        }
        buttonSave.setOnClickListener {
            viewModel.changeShopItem(editName.text?.toString(), editCount.text?.toString())

        }
    }

    private fun launchAddMod() {
        buttonSave.setOnClickListener {
            viewModel.addShopItem(editName.text?.toString(), editCount.text?.toString())

        }
    }

    private fun parseParam() {
        if (screenMod != EXTRA_CHANGE && screenMod != EXTRA_ADD)
            throw RuntimeException("Cant identify MODE")


        if (screenMod == EXTRA_CHANGE && shopItemId == ShoppingItem.UNDEFINE_ID) {
            throw RuntimeException("No id in intent")
        }
    }

    private fun initViews() {
        tilName = viewFragment.findViewById(R.id.til_name)
        tilCount = viewFragment.findViewById(R.id.til_count)
        editName = viewFragment.findViewById(R.id.et_name)
        editCount = viewFragment.findViewById(R.id.et_count)
        buttonSave = viewFragment.findViewById(R.id.saveButton)
    }

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item"
        private const val EXTRA_ADD = "mode_add"
        private const val EXTRA_CHANGE = "mode_change"
        private const val EXTRA_UNKNOWN = ""
        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShoppingItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, EXTRA_ADD)
            return intent
        }
          fun newInstanceAddItem():ShopItemFragment
        {
            return ShopItemFragment(EXTRA_ADD)
        }
          fun newInstanceChangeItem(shopItemId:Int):ShopItemFragment
        {
            return ShopItemFragment(EXTRA_CHANGE,shopItemId)
        }
    }


}






