package ru.thever4.lessons.lesson4_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.thever4.lessons.lesson4_2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var adapter: ChatsAdapter = ChatsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding?.chatsRecyclerView) {
            this ?: return
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
            addItemDecoration(createItemDecorator())
        }

        adapter.submitList(ChatDataStorage.userChats)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    // Декораторы можно использовать для того, чтобы разнообразить промежуток между элементами
    // списка. Таким образом можно сделать полосы как у нас, либо добавить разнообразные отступы
    // и даже напрямую получить доступ до canvas нашего recyclerview, чтобы отрисовать там
    // что заблагорассудится.
    //
    // Безусловно, можно было бы включить эти подчёркивания прямо в вёрстку каждого item'а,
    // но так правильнее, поскольку это зона ответственности именно декоратора.
    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.chat_item_decorator)
                ?.let { this@apply.setDrawable(it) }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        // Все ссылки на binding необходимо удалять после завершения фрагмента,
        // чтобы Garbage Collector смог собрать мусор в виде утерянных View-объектов.
        // Ссылаться на binding фрагмента вне самого фрагмента также не стоит,
        // равно как и порождать ситуации, когда один Fragment может явно хранить ссылку
        // на любой другой Fragment. Для этих целей есть fragmentManager.
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }

}