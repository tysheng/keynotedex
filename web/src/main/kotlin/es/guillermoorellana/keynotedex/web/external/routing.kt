@file:JsModule("react-router-dom")

package es.guillermoorellana.keynotedex.web.external

import react.*

@JsName("HashRouter")
external class HashRouterComponent : Component<RProps, RState> {
    override fun render(): ReactElement?
}

@JsName("BrowserRouter")
external class BrowserRouterComponent : Component<RProps, RState> {
    override fun render(): ReactElement?
}

@JsName("Switch")
external class SwitchComponent : Component<RProps, RState> {
    override fun render(): ReactElement?
}

@JsName("Route")
external class RouteComponent : Component<RouteProps, RState> {
    override fun render(): ReactElement?
}

@JsName("Link")
external class LinkComponent : Component<LinkProps, RState> {
    override fun render(): ReactElement?
}

@JsName("Redirect")
external class RedirectComponent : Component<RedirectProps, RState> {
    override fun render(): ReactElement?
}

external interface RouteProps : RProps {
    var path: String
    var exact: Boolean
    var component: RClass<RProps>
    var render: (props: RouteResultProps<*>) -> ReactElement
}

external interface LinkProps : RProps {
    var to: String
    var className: String
}

external interface RouteResultProps<T : RProps> : RProps {
    var match: RouteResultMatch<T>
    // location
    // history
}

external interface RedirectProps : RProps {
    var to: String
}

external interface RouteResultMatch<T : RProps> {
    var url: String
    var path: String
    var params: T
}
