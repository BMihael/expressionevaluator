# Expressionevaluator
##### Hiring project for lpws

* Tehnologies :
    * Java 11
     * Maven
    * h2 Database
  

* Key-features:
  * Tree Manipulation:</br>
  In this project, a crucial aspect involves the manipulation of a tree structure to evaluate logical expressions. The tree structure is designed with logical operators as parent nodes and operands as their children. Each node represents a logical operator, and its children are the operands that need to be evaluated. To determine which logical operator becomes the parent node, a dynamic stack is employed. This stack assists in identifying the logical operator with the fewest parentheses surrounding it. Once the parent operator is determined, the input string is split into two halves. The first half is assigned to the left node, while the second half goes to the right node. This process is repeated as long as there is more than one logical operator within the string.
  * String manipulation using Stack: </br>
    In this project, a key feature involves the utilization of a stack for efficiently managing the hierarchy of parentheses within the logical expression. The stack is employed to identify the number of parentheses between each logical operator. As the expression is parsed, each time an opening parenthesis "(" is encountered, a counter is incremented, and when a closing parenthesis ")" is encountered, the counter is decremented. To effectively capture this information, a specialized class called StackNodeWrapper is used. This class stores data about each node, its associated level, and its index within the expression. When a logical operator is encountered, the number of parentheses, also known as the level, that has been incremented or decremented is written into the StackNodeWrapper. The stack is crucial in this context as it is emptied gradually during the evaluation process, ensuring that the elements are handled appropriately. It continues to pop elements from the stack until it either becomes empty or encounters the logical operator. This approach optimizes memory management throughout the logical expression evaluation process, ensuring the efficient processing of parentheses and logical operators.