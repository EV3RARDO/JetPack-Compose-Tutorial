


 ## Jetpack Compose Tutorials:
* (https://www.youtube.com/watch?v=qvDo0SKR8-k&t=271s)
* (https://www.youtube.com/watch?v=mymWGMy9pYI)
* (https://www.youtube.com/watch?v=rmv2ug-wW4U)
* (https://www.youtube.com/watch?v=k3jvNqj4m08)
* (https://www.youtube.com/watch?v=25VjadHR0zs)
* (https://www.youtube.com/watch?v=eL3G1Basx1o)
* (https://developer.android.com/jetpack/compose/state)
* (https://developer.android.com/courses/pathways/compose)
* (https://developer.android.com/codelabs/jetpack-compose-state#0)
* (https://developer.android.com/jetpack)
* (https://codingtroops.com/author/catalinghita8/
* (https://medium.com/@takahirom/jetpack-compose-state-guideline-494d467b6e76)
* (https://proandroiddev.com/traditional-mvvm-with-jetpack-compose-and-stateflow-7ca934e12784)


  


## Compose State:
* Persist state across re-composition: `remember`
* Persist state across configuration changes: `rememberSaveable`
* State created in composables needs to be remembered.
* Hoist state to at least the lowest common ancestor of its consumers.


* _Hoist the state_ for re-usability and testabilit y
* Use ViewModel with LiveData and `observeAsState`

## Key Concepts:
* Single Source of Truth Principle
* State Hoisting (State down, Events up)  - re-usable, testable, loose couple
* Stateless Composables (more re-usable) vs Stateful Composables

## Compose Side Effect:


## Notes:

![img](.screenshots/state-ui.png)

![img](.screenshots/ui-state.png)

![img](.screenshots/single-source-of-truth.png)

![state hoisting](.screenshots/State_Hoisting.png)

![state hoisting](.screenshots/pass_only_what_is_needed.png)

![stateful_vs_stateless](.screenshots/stateful_vs_stateless.png)

![states_and_logic](.screenshots/state_and_logic.png)

![viewModels_as_source_of_truth](.screenshots/viewModels_as_source_of_truth.png)

![viewModels_vs_state_holders](.screenshots/viewModes_vs_state_holders.png)

![viewModels_vs_state_holders](.screenshots/managing_state.png)

![img](.screenshots/mvvm-state.png)

![img](.screenshots/recomposition.png)

![img](.screenshots/recomposition-mvvm.png)

![img](.screenshots/where-to-hold-state.png)

