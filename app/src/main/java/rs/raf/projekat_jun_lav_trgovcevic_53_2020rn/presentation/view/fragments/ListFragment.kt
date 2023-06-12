package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import androidx.fragment.app.Fragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R

class ListFragment : Fragment(R.layout.fragment_list) {

//    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
//    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
//
//    private var _binding: FragmentListBinding? = null
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    private lateinit var adapter: MovieAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        initUi()
//        initObservers()
//    }
//
//    private fun initUi() {
//        initRecycler()
//        initListeners()
//    }
//
//    private fun initRecycler() {
//        binding.listRv.layoutManager = LinearLayoutManager(context)
//        adapter = MovieAdapter()
//        binding.listRv.adapter = adapter
//    }
//
//    private fun initListeners() {
//        binding.inputEt.doAfterTextChanged {
//            val filter = it.toString()
//            mainViewModel.getMoviesByName(filter)
//        }
//    }
//
//    private fun initObservers() {
//        mainViewModel.moviesState.observe(viewLifecycleOwner, Observer {
//            Timber.e(it.toString())
//            renderState(it)
//        })
//        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
//        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
//        // koji zadovoljavaju query
//        mainViewModel.getAllMovies()
//        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
//        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
//        // preko metode getAllMovies()
//        mainViewModel.fetchAllMovies()
//    }
//
//    private fun renderState(state: CategoriesState) {
//        when (state) {
//            is CategoriesState.Success -> {
//                showLoadingState(false)
//                adapter.submitList(state.movies)
//            }
//            is CategoriesState.Error -> {
//                showLoadingState(false)
//                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
//            }
//            is CategoriesState.DataFetched -> {
//                showLoadingState(false)
//                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
//            }
//            is CategoriesState.Loading -> {
//                showLoadingState(true)
//            }
//        }
//    }
//
//    private fun showLoadingState(loading: Boolean) {
//        binding.inputEt.isVisible = !loading
//        binding.listRv.isVisible = !loading
//        binding.loadingPb.isVisible = loading
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}