object CoffeeMachine {
    var water = 400
    var milk = 540
    var beans = 120
    var money = 550
    var disposableCups = 9
}

sealed class Coffee(
    val water: Int, val milk: Int, val beans: Int, val price: Int
) {
    object Espresso : Coffee(250, 0, 16, 4)
    object Latte : Coffee(350, 75, 20, 7)
    object Cappuccino : Coffee(200, 100, 12, 6)
}

fun main() {
    buyFillTake()
    main()
}

fun buyFillTake() {
    println("Write action (buy, fill, take, remaining, exit): ")
    when (readln()) {
        "buy" -> {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
            when (readln()) {
                "1" -> buy(Coffee.Espresso)
                "2" -> buy(Coffee.Latte)
                "3" -> buy(Coffee.Cappuccino)
                "back" -> buyFillTake()
                else -> buyFillTake()
            }
        }

        "fill" -> fill()
        "take" -> take()
        "remaining" -> coffeeMachineState()
        "exit" -> System.exit(0)
        else -> buyFillTake()
    }
}

fun buy(sort: Coffee) {
    val less0 = mutableListOf<String>()
    if (CoffeeMachine.water - sort.water < 0) less0.add("water")
    if (CoffeeMachine.milk - sort.milk < 0) less0.add("milk")
    if (CoffeeMachine.beans - sort.beans < 0) less0.add("coffee beans")
    if (CoffeeMachine.disposableCups < 0) less0.add("disposable cups")

    if (less0.isEmpty()) {
        CoffeeMachine.water -= sort.water
        CoffeeMachine.milk -= sort.milk
        CoffeeMachine.beans -= sort.beans
        CoffeeMachine.disposableCups--
        CoffeeMachine.money += sort.price
        println("I have enough resources, making you a coffee!")
    } else println("Sorry, not enough ${less0.joinToString(", ")}!")
}

fun fill() {
    fun readAmount(message: String) = println("Write how many $message you want to add: ").let { readln().toInt() }
    CoffeeMachine.water += readAmount("ml of water")
    CoffeeMachine.milk += readAmount("ml of milk")
    CoffeeMachine.beans += readAmount("grams of coffee beans")
    CoffeeMachine.disposableCups += readAmount("disposable cups")
}

fun take() = println("I gave you $${CoffeeMachine.money}").let { CoffeeMachine.money = 0 }

fun coffeeMachineState() {
    println(
        """
        The coffee machine has:
        ${CoffeeMachine.water} ml of water
        ${CoffeeMachine.milk} ml of milk
        ${CoffeeMachine.beans} g of coffee beans
        ${CoffeeMachine.disposableCups} disposable cups
        $${CoffeeMachine.money} of money
    """.trimIndent()
    )
}