# Hand Over of Outstanding Issues

As part of the maintainer's handover on leaving, please note that issues reported by checkstyle
need to be fixed IN ORDER TO BUILD THE PROJECT.

The exact rectification is IN the error message. Please read it. You will see the same error 
message when running ```mvnw test``` Since the output of that command represents the current state
of the code - use that as your main source.

You will not be able to build the project and run tests until these are fixed.
Please remove issues from this file as you fix them!


# Splitting one variable declaration into several



## Product (String Field declarations)

```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/domain/model/Product.java:[17] (whitespace) EmptyLineSeparator: ',' should be separated from previous statement.
```
```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/domain/model/Product.java:[17,5] (coding) MultipleVariableDeclarations: Each variable declaration must be in its own statement.
```


# Never use an **IF** without {}'s 

## Product::equals

```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/domain/model/Product.java:[77] (blocks) NeedBraces: 'if' construct must use '{}'s.
```


# Shorten long lines

## Cart::findProductInCartLines
```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/domain/model/Cart.java:[89] (sizes) LineLength: Line is longer than 120 characters (found 124).
```

# Move the { to the same line as the method declaration
_Standard Java Style: opening brace is on the same line, not only for the blocks inside a function, but also for class or method declarations_

## ProductController::createProduct
```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/controller/ProductController.java:[47,5] (blocks) LeftCurly: '{' at column 5 should be on the previous line.
```

## ProductController::deleteProduct
```
[WARNING] src/main/java/com/openclassrooms/lambazon/store/controller/ProductController.java:[62,5] (blocks) LeftCurly: '{' at column 5 should be on the previous line.
```
