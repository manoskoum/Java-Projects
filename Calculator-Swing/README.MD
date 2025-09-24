# Swing Calculator
A small calculator built with **Java Swing**.  
It mimics a mobile-style layout and coloring.

## Features
- Chain operations (e.g., `3 + 3 + 3 + …`) — the calculation happens when you press **`=`**
- **Left-to-right** evaluation (no operator precedence yet)
- Decimal **comma** (`,`) input
- `%` applies to the **current number** being typed
- `()` are visual only for now (not evaluated)
- Top row and right column styled light blue, digit keys light grey, `=` purple

## Requirements
- Java **8+**
- (Optional) IntelliJ IDEA (Community or Ultimate)

## Run in IntelliJ IDEA
1. **Open** the project folder in IntelliJ.
2. Make sure **Project SDK** is set (File → Project Structure → Project).
3. If you have a `src` folder, **Mark Directory as → Sources Root**.
4. Ensure the `Calculator` class has a `main` method:
   ```java
   public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(Calculator::new);
   }

## Screenshot
