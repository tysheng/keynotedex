package es.guillermoorellana.keynotedex.web.components

import kotlinx.html.main
import react.*
import react.dom.h1

class SpeakersView : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        main {
            attrs { role = "main" }
            h1 { +"Speakers" }
        }
    }
}
