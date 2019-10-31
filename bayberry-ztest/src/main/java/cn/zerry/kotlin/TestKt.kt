package cn.zerry.kotlin

import org.apache.http.util.Args
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.io.BufferedReader
import java.io.StringReader
import java.time.LocalDate
import java.time.Period
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

/**
 * @Description TODO
 * @author linzengrui
 * @date 2018年05月07日 14:52
 */

//data class Person(val name: String, val age: Int?= null)

//fun max(a: Int, b: Int): Int{
//    return if(a > b) a else b
//}

//class Person(
//        val name: String,
//        val isMarried: Boolean
//)

//class Rectangle(val height: Int, val width: Int){
//    val isSquare:Boolean
//        get(){
//            return height == width
//        }
//}

//fun rec(c: Char) = when(c){
//    in '0'..'9' -> "It is a digit"
//    in 'a'..'z', in 'A'..'Z' -> "It is a letter"
//    else -> "do not know"
//}

//fun readNumber(reader: BufferedReader){
//    val number = try {
//        Integer.parseInt(reader.readLine())
//    }catch (e: NumberFormatException){
//
//    }
//}

//fun String.lastChar(): Char = this.get(this.length - 1)


//fun main(args: Array<String>){
//    val persons = listOf<Person>(Person("Alice"), Person("Bob", age = 29))
//    val oldest = persons.maxBy { it.name ?: "" }
//    println("what you need is ${oldest}")

//    println(max(1,2))

//    val rectangle = Rectangle(2, 3)
//    println(rectangle.isSquare)

//    println(rec('b'))

//    val reader = BufferedReader(StringReader("not a number"))
//    println(readNumber(reader))


//    val set = hashSetOf(1, 7, 53)
//    val list = arrayListOf(1, 7, 53)
//    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
//    println(set.javaClass)
//    println(list.javaClass)
//    println(map.javaClass)

//    val strings = listOf("first", "second", "fourteenth")
//    println(strings.last())
//    println(strings.first())

//    val list = listOf(1, 2, 3)
//    println(joinToString(list, ";", "(", ")"))

//    println("Kotlin".lastChar())

//}

//
//fun <T> joinToString(
//        collection: Collection<T>,
//        seperator: String,
//        prefix: String,
//        postfix: String
//
//): String{
//    val result = StringBuffer(prefix)
//    for((index, element) in collection.withIndex()){
//        if(index > 0) result.append(seperator)
//        result.append(element)
//    }
//    result.append(postfix)
//    return result.toString()
//}


//open class View(){
//    open fun click() = println("View clicked")
//}
//
//class Button: View(){
//    override fun click() = println("Button clicked")
//}
//
//fun main(args : Array<String>){
//    val view : View = Button()
//    view.click()
//}


//val String.lastChar : Char
//    get() = get(length -1)
//var StringBuilder.lastChar: Char
//    get() = get(length - 1)
//    set(value: Char) {
//        this.setCharAt(length - 1, value)
//    }




//fun main(args: Array<String>){
//    println("Kotlin".lastChar)
//    val sb = StringBuilder("Kotlin?")
//    sb.lastChar = '!'
//    println(sb)

//    val list = listOf("args: ", *args)
//    println(list)

//    println("12.345-6.A".split("\\.|-".toRegex()))

//}


//data class Person(val name: String, val age: Int)
//
//fun main(args: Array<String>){
//    val createPerson = ::Person
//    val p = createPerson("Alice", 29)
//    println(p.age)
//}

//data class Person(val name: String, val age: Int)

//val choose = { p: Person -> p.age < 27}

//fun main(args: Array<String>){
//    val list = listOf(Person("Alice", 24), Person("Peter", 22))
//    println(list.map { it.name })

//    val map = mapOf(1 to "key1", 2 to "key2")
//    println(map.mapValues { it.value.toLowerCase() })

//    println(list.all(choose))
//    println(list.find(choose))
//
//    val number = listOf(1, 2, 3)
//    println(!number.all { it == 3 } )
//    println(number.any { it != 3})

//    val list = listOf("a", "ab", "b")
//    println(list.groupBy (String::first))
//    println(list.flatMap { it.toList() })

//    listOf(1, 2, 3, 4).asSequence()
//            .map { println("map($it)"); it * it }
//            .filter { println("filter($it)"); it % 2 == 0 }
//            .toList()
//}

//fun printAllCaps(s:String?){
//    val case = s?.toUpperCase()
//    println(case)
//}
//
//fun main(args: Array<String>){
//    printAllCaps("abc")
//    printAllCaps(null)
//}

//class Employee(val name: String, val manage: Employee?)
//
//fun managerName(employee: Employee):String? = employee.manage?.name
//
//fun main(args:Array<String>){
//    val ceo = Employee("alfred", null)
//    val dev = Employee("bob", ceo)
//    println(managerName(ceo))
//    println(managerName(dev))
//}


//class Address(val streetAddress: String, val zipCode: Int, val city:String, val country:String)
//class Company(val name: String, val address: Address?)
//class Person(val name: String, val company: Company?)
//fun Person.countryName():String{
//    val country = this.company?.address?.country
//    return if(country != null) country else "unkown"
//}
//fun main(args: Array<String>){
//    val person = Person("alfred", null)
//    println(person.countryName())
//}


//fun readNumber(reader: BufferedReader): List<Int?>{
//    val result = ArrayList<Int?>();
//    for (line in reader.lineSequence()){
//        try {
//            val number = line.toInt()
//            result.add(number)
//        }catch (e: NumberFormatException){
//            result.add(null)
//        }
//    }
//    return result
//}
//
//fun addValidNumbers(numbers: List<Int?>){
////    var sumOfValidNumbers = 0
////    var invalidNumbers = 0
////    for(number in numbers){
////        if (number != null){
////            sumOfValidNumbers += number
////        }else{
////            invalidNumbers ++
////        }
////    }
////    println("Sum of valid numbers: $sumOfValidNumbers")
////    println("Invalid numbers: $invalidNumbers")
//
//    val validNumber = numbers.filterNotNull()
//    println("Sum of valid numbers: ${validNumber.sum()}")
//    println("Invalid numbers: ${numbers.size - validNumber.size}")
//}
//
//fun main(args: Array<String>) {
//    val bf = BufferedReader(StringReader("1\nabc\n42"))
//    val readNumber = readNumber(bf)
//    addValidNumbers(readNumber)
//
//}

//fun main(args: Array<String>) {
//    val strings = listOf("a", "b", "c")
//    println("%s/%s/%s".format(*strings.toTypedArray()))
//
//    val squares = IntArray(5) { i -> (i+1) * (i+1) }
//    println(squares.joinToString())
//}


//class Email{}
//class Person(val name: String){
//    private var _emails: List<Email>? = null
////    val emails: List<Email>
////        get() {
////            if(_emails == null){
////                _emails = loadEmails(this)
////            }
////            return _emails!!
////        }
//    val emails by lazy { loadEmails(this) }
//}
//
//fun loadEmails(person: Person): List<Email>{
//    println("load email for ${person.name}")
//    return listOf()
//}
//
//fun main(args: Array<String>) {
//    val p = Person("Alice")
//    p.emails
//    p.emails
//}


//open class PropertyChangeAware{
//    protected val changesupport = PropertyChangeSupport(this)
//    fun addPropertyChangeListener(listener: PropertyChangeListener){
//        changesupport.addPropertyChangeListener(listener)
//    }
//    fun removePropertyChangeListener(listener: PropertyChangeListener){
//        changesupport.removePropertyChangeListener(listener)
//    }
//}
//
//class Person(
//        val name: String, age: Int, salary: Int
//):PropertyChangeAware(){
//
//    private val observer = {
//        prop: KProperty<*>, oldValue: Int, newValue: Int ->
//        changesupport.firePropertyChange(prop.name, oldValue, newValue)
//    }
//
//    var age: Int by Delegates.observable(age, observer)
//    var salary: Int by Delegates.observable(salary, observer)
//}
//
//fun main(args: Array<String>) {
//    val p = Person("Alfred", 23, 3000)
//    p.addPropertyChangeListener(
//            PropertyChangeListener { event ->
//                println("Property ${event.propertyName} changed " +
//                        "from ${event.oldValue} to ${event.newValue}")
//            }
//    )
//    p.age = 30
//    p.salary = 4000
//
//}


//class Person{
//    private val _attributes = hashMapOf<String, String>()
//    fun setAttribute(attrName: String, value: String){
//        _attributes[attrName] = value
//    }
//    val name: String by _attributes
//}
//
//fun main(args: Array<String>) {
//    val p = Person()
//    val data = mapOf("name" to "Alfred", "company" to "LV")
//    for ((attrName, value) in data)
//        p.setAttribute(attrName, value)
//    println(p.name)
//}

//val <T> List<T>.penultimate: T
//    get() = this[size - 2]

//fun <T: Number> oneHalf(value: T): Double{
//    return value.toDouble() / 2.0
//}

//fun <T: Comparable<T>> max(first: T, second: T): T{
//    return if (first > second) first else second
//}

//fun <T> ensureTrallingPeriod(seq: T)
//    where T: CharSequence, T: Appendable{
//    if(!seq.endsWith('.')){
//        seq.append('.')
//    }
//}

//fun main(args: Array<String>) {
//    val letters = ('a'..'z').toList()
//    println(letters.slice<Char>(0..2))
//    println(letters.slice(10..13))

//    println(listOf(1, 2, 3, 4).penultimate)

//    println(oneHalf(3))

//    println(max("kotlin", "java"))

//    val helloworld = StringBuilder("Hello World")
//    ensureTrallingPeriod(helloworld)
//    println(helloworld)

//}


//fun performRequest(
//        url : String,
//        callback: (code: Int, content: String) -> Unit
//){
//
//}
//
//fun main(args: Array<String>) {
//    val url = "http://kotlin"
//    performRequest(url){code, content ->  }
//    performRequest(url){code, page ->  }
//
//}


//fun printSum(c: Collection<Int>){
//    if(c is List<Int>){
//        println(c.sum())
//    }
//}
//
//fun main(args: Array<String>) {
//    printSum(listOf(1, 2, 3))
//}


//fun main(args: Array<String>) {
//    val items = listOf("one", 2, "three")
//    println(items.filterIsInstance<Char>())
//}


//fun printContents(list: List<Any>){
//    println(list.joinToString())
//}
//
//fun main(args: Array<String>) {
//    printContents(listOf("abc", "def"))
//}


//fun<T> copyData(source: MutableList<out T>, destination: MutableList<T>){
//    for (item in source){
//        destination.add(item)
//    }
//}
//
//fun main(args: Array<String>) {
//    val ints = mutableListOf(1, 2, 3)
//    val anyItems = mutableListOf<Any>()
//    copyData(ints, anyItems)
//    println(anyItems)
//}


//fun <T> printFirst(list: List<T>){
//    if(list.isNotEmpty()){
//        println(list.first())
//    }
//}
//
//fun main(args: Array<String>) {
//    printFirst(listOf("alfred", "bob"))
//}


//interface FieldValidator<in T>{
//    fun validate(input: T): Boolean
//}
//
//object DefaultStringValidator: FieldValidator<String>{
//    override fun validate(input: String) = input.isNotEmpty()
//}
//
//object DefaultIntValidator: FieldValidator<Int>{
//    override fun validate(input: Int) = input >= 0
//}
//
//object Validators{
//    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
//    fun <T: Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>){
//        validators[kClass] = fieldValidator
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    operator fun <T: Any> get(kClass: KClass<T>): FieldValidator<T> =
//            validators[kClass]as? FieldValidator<T>
//    ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
//}

//fun main(args: Array<String>) {
////    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
////    validators[String::class] = DefaultStringValidator
////    validators[Int::class] = DefaultIntValidator
//
//    Validators.registerValidator(String::class, DefaultStringValidator)
//    Validators.registerValidator(Int::class, DefaultIntValidator)
//    println(Validators[String::class].validate("Kotlin"))
//    println(Validators[Int::class].validate(42))
//}

//fun foo(x: Int) = print(x)
//
//fun main(args: Array<String>) {
//    val kFunction = ::foo
//    kFunction.call(42)
//}

//class Person(val name: String, val age: Int)
//fun main(args: Array<String>) {
//    val person = Person("Alice", 29)
//    val kClass = person.javaClass.kotlin
//    println(kClass.simpleName)
//    kClass.memberProperties.forEach { println(it.name) }
//}


//var counter = 0
//fun main(args: Array<String>) {
//    val kProperty = ::counter
//    kProperty.setter.call(21)
//    println(kProperty.get())
//}


//class Person(val name:String, val age:Int)
//
//fun main(args: Array<String>) {
//    val person = Person("alfred", 22)
//    val memberProperty  = Person::age
//    println(memberProperty.get(person))
//}



//fun buildString(
//        buidlerAction: StringBuilder.() -> Unit
//):String{
//    val sb = StringBuilder()
//    buidlerAction(sb)
//    return sb.toString()
//}
//
////fun main(args: Array<String>) {
////    val s = buildString {
////        this.append("hello, ")
////        append("world")
////    }
////    println(s)
////}
//
//val appendExcl: StringBuilder.() -> Unit = {this.append(",")}
//
//fun main(args: Array<String>) {
//    val stringBuilder = StringBuilder("hi")
//    stringBuilder.appendExcl()
//    println(stringBuilder)
//    println(buildString(appendExcl))
//}


//fun main(args: Array<String>) {
//    val map = mutableMapOf(1 to "one")
//    map.apply { this[2] = "two" }
//    with(map){this[3] = "three"}
//    println(map)
//}


//open class Tag(val name: String){
//    private val children = mutableListOf<Tag>()
//    protected fun <T: Tag> doInit(child: T, init: T.()->Unit){
//        child.init()
//        children.add(child)
//    }
//
//    override fun toString(): String = "<$name>${children.joinToString()}</$name>"
//}
//
//fun table(init: TABLE.()->Unit) = TABLE().apply(init)
//
//class TABLE: Tag("table"){
//    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
//}
//
//class TR: Tag("tr"){
//    fun td(init: TD.() -> Unit) = doInit(TD(), init)
//}
//
//class TD: Tag("td")
//
//fun createTable()=
//        table {
//            tr {
//                td {
//
//                }
//            }
//        }
//
//fun createAnotherTable() = table {
//    for(i in 1..2){
//        tr {
//            td {
//
//            }
//        }
//    }
//}
//
//
//fun main(args: Array<String>) {
////    println(createTable())
//    println(createAnotherTable())
//}


//class Greeter(val greeting: String){
//    operator fun invoke(name:String){
//        println("$greeting, $name")
//    }
//}
//
//fun main(args: Array<String>) {
//    val greet = Greeter("alfred")
//    greet("bob")
//}



//data class Issue(val id: String, val project: String, val type: String, val priority: String, val description: String)
//
//class ImportantIssuesPredicate(val project: String): (Issue) -> Boolean{
//    override fun invoke(p1: Issue): Boolean {
//        return p1.project == project && p1.isImportant()
//    }
//
//    private fun Issue.isImportant(): Boolean{
//        return type == "Bug" && (priority == "Major" || priority == "Critical")
//    }
//}
//
//fun main(args: Array<String>) {
//    val i1 = Issue("IDEA-154446", "IDEA", "Bug", "Major",
//            "Save settings failed")
//    val i2 = Issue("KT-12183", "Kotlin", "Feature", "Normal",
//            "Intention: convert several calls on the same receiver to with/apply")
//    val predicate = ImportantIssuesPredicate("IDEA")
//    for (issue in listOf(i1, i2).filter(predicate)){
//        println(issue.id)
//    }
//}


//class DependencyHandler {
//    fun compile(coordinate: String) {
//        println("Added dependency on $coordinate")
//    }
//
//    operator fun invoke(
//            body: DependencyHandler.() -> Unit) {
//        body()
//    }
//}
//
//fun main(args: Array<String>) {
//    val dependencies = DependencyHandler()
//    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")
//    dependencies {
//        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
//    }
//}



val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this

fun main(args: Array<String>) {
    println(1.days.ago)
    println(1.days.fromNow)
}






