package chapter9.sec2_3

import chapter9.sec2_2.ParseError
import chapter9.sec2_2.Parser
import chapter9.sec2_2.Parsers

abstract class Listing : Parsers<ParseError> {

    val listing = {
        fun <A, B, C> map2(
            pa: Parser<A>,
            pb: Parser<B>,
            f: (A, B) -> C
        ): Parser<C> = TODO()

        //tag::init1[]
        infix fun <T> T.cons(la: List<T>): List<T> = listOf(this) + la

        fun <A> many(pa: Parser<A>): Parser<List<A>> =
            or(
                map2(pa, many(pa)) { a, la -> a cons la },
                succeed(emptyList())
            )
        //end::init1[]

        val p = char('a')
        //tag::init2[]
        many(p)
        map2(p, many(p)) { a, la -> a cons la }
        map2(p,
            map2(p, many(p)) { a, la -> a cons la }) { a, la -> a cons la }
        //end::init2[]
    }
}