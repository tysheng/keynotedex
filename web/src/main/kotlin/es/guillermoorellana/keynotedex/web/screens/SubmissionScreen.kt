package es.guillermoorellana.keynotedex.web.screens

import es.guillermoorellana.keynotedex.web.comms.getSubmission
import es.guillermoorellana.keynotedex.web.components.editable.ChangeEvent
import es.guillermoorellana.keynotedex.web.components.editable.editableText
import es.guillermoorellana.keynotedex.web.components.editable.editableTextArea
import es.guillermoorellana.keynotedex.web.components.editable.get
import es.guillermoorellana.keynotedex.web.external.RouteResultProps
import es.guillermoorellana.keynotedex.web.loading
import es.guillermoorellana.keynotedex.web.model.Submission
import kotlinx.coroutines.experimental.promise
import react.*
import react.dom.div
import react.dom.h3
import react.dom.p

class SubmissionScreen : RComponent<RouteResultProps<SubmissionRouteProps>, SubmissionViewState>() {

    override fun componentDidMount() {
        fetchSubmission()
    }

    override fun RBuilder.render() {
        div("container") {
            loading(state.submission) {
                with(it) {
                    h3 {
                        editableText {
                            attrs {
                                value = title
                                propName = "title"
                                change = { chg: ChangeEvent -> onChangeEvent(chg) }
                            }
                        }
                    }
                    editableTextArea {
                        attrs {
                            value = abstract
                            propName = "abstract"
                            change = { chg: ChangeEvent -> onChangeEvent(chg) }
                        }
                    }
                    type.let { if (it.isNotEmpty()) p { +"Type $it" } }
                    submittedTo.let { if (it.isNotEmpty()) p { +"Submitted to $it" } }
                }
            }
        }
    }

    private fun onChangeEvent(chg: ChangeEvent) {
        setState {
            chg["abstract"]?.let { abstract ->
                submission = submission?.copy(abstract = abstract)
            }
            chg["title"]?.let { title ->
                submission = submission?.copy(title = title)
            }
        }
    }

    private fun fetchSubmission() {
        promise {
            val submission = getSubmission(idFromRoute(props.match.params.submissionId))
            setState {
                this.submission = submission
            }
        }.catch {
            console.error(it)
        }
    }

    companion object {
        const val route = "/:userId/:submissionId"
    }
}

private fun idFromRoute(submissionRoute: String) = submissionRoute.substringBefore('-')

external interface SubmissionRouteProps : RProps {
    val userId: String
    val submissionId: String
}

external interface SubmissionViewState : RState {
    var submission: Submission?
}
