package co.uk.redpixel.vendingmachine.console

import com.xenomachina.argparser.HelpFormatter
import com.xenomachina.text.term.codePointWidth
import com.xenomachina.text.term.columnize
import com.xenomachina.text.term.wrapText

class DispenseHelpFormatter : HelpFormatter {

    override fun format(progName: String?, columns: Int, values: List<HelpFormatter.Value>): String {
        return Help.of {
            programName = progName!!
            arguments = values
            width = columns
        }
    }

    private class Help private constructor(private val name: String,
                                           private val required: List<HelpFormatter.Value>,
                                           private val optional: List<HelpFormatter.Value>,
                                           private val positional: List<HelpFormatter.Value>,
                                           private val columnWidth: Int,
                                           private val totalWidth: Int) {

        companion object {
            const val indent = "  "
            val indentWidth: Int = indent.codePointWidth()

            fun formatArgument(option: HelpFormatter.Value) =
                    option.usages.joinToString(", ") { it.replace(' ', '\u00a0') }

            fun of(init: Builder.() -> Unit): String {
                val help = Builder(init).build()
                return help.toString()
            }
        }

        override fun toString() = printUsage()
                .plus(printArguments("required", required, columnWidth, totalWidth))
                .plus(printArguments("optional", optional, columnWidth, totalWidth))
                .plus(printArguments("positional", positional, columnWidth, totalWidth))

        private fun printUsage() = buildString {
            append("usage: $name")
            required.forEach { append(" ${it.usages.joinToString("|", prefix = "(", postfix = ")")}") }
            optional.forEach { append(" ${it.usages.joinToString("|", prefix = "[", postfix = "]")}") }
            positional.forEach { append(" ${it.usages.joinToString("|")}") }
            appendln()
        }

        private fun printArguments(name: String,
                                   arguments: List<HelpFormatter.Value>,
                                   columnWidth: Int, totalWidth: Int) = buildString {
            if (arguments.isNotEmpty()) {
                appendln()
                appendln("$name arguments")
                for (arg in arguments) {
                    val leftColumn = formatArgument(arg).wrapText(columnWidth - indentWidth).prependIndent(indent)
                    val rightColumn = arg.help.wrapText(totalWidth - columnWidth - 2 * indentWidth).prependIndent(indent)
                    appendln(columnize(leftColumn, rightColumn, minWidths = intArrayOf(columnWidth)))
                }
            }
        }

        class Builder private constructor() {
            lateinit var programName: String
            lateinit var arguments: List<HelpFormatter.Value>

            var width: Int = Int.MAX_VALUE
                set(value) {
                    field = when {
                        value < 0 -> throw IllegalArgumentException("columns must be non-negative")
                        value == 0 -> Int.MAX_VALUE
                        else -> value
                    }
                }

            constructor(init: Builder.() -> Unit) : this() {
                init()
            }

            fun build(): Help {
                val required = mutableListOf<HelpFormatter.Value>()
                val optional = mutableListOf<HelpFormatter.Value>()
                val positional = mutableListOf<HelpFormatter.Value>()

                for (argument in arguments) {
                    when {
                        argument.isPositional -> positional
                        argument.isRequired -> required
                        else -> optional
                    }.add(argument)
                }

                val columnWidth = 2 * indentWidth - 1 + if (width == 0) {
                    arguments.map { formatArgument(it).length }.max() ?: 0
                } else {
                    // Make left column as narrow as possible without wrapping any of the individual usages, though no wider than
                    // half the screen.
                    arguments.map { formatArgument(it).split(" ")
                             .map { it.length }.max() ?: 0 }.max() ?: 0.coerceAtMost(width / 2)
                }

                return Help(programName, required, optional, positional, columnWidth, width)
            }
        }
    }
}
