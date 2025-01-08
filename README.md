# kotlin-nullpointerexception-ex-two

Vamos analisar a nova saída apresentada na imagem e o código fornecido para entender o comportamento, a diferença entre Kotlin e Java, e por que o Kotlin oferece vantagens no tratamento de referências nulas.

---

## **1. Análise da Nova Saída**

### **Saída na Imagem:**
```
This isn't null
false
```

### **Razões da Saída no Código Kotlin:**
1. **Primeira Linha:**
   ```kotlin
   str?.let { printText(it) }
   ```
   - **O que acontece?**
     - `str` é `"This isn't null"` (não é `null`).
     - O operador **`?.`** verifica que `str` não é `null` e executa a função `printText`.
     - A função imprime o texto `"This isn't null"`.

   - **Saída:**
     ```
     This isn't null
     ```

2. **Segunda Linha:**
   ```kotlin
   println(str4 == anotherStr)
   ```
   - **O que acontece?**
     - `str4` é `null` e `anotherStr` é `"This isn't nullable"`.
     - O operador `==` no Kotlin é **null-safe**:
       - Retorna `false` quando um dos operandos é `null` e o outro não.
     - Por isso, o resultado é `false`.

   - **Saída:**
     ```
     false
     ```

3. **Resto do Código:**
   ```kotlin
   val str2 = str!!
   val str3 = str2.toUpperCase()
   ```
   - **O que acontece?**
     - `str!!` força o acesso a `str` como não nulo.
     - Como `str` contém `"This isn't null"`, o código funciona sem erros.
     - O valor `"This isn't null"` é convertido para letras maiúsculas, mas como o resultado não é exibido com `println`, ele não aparece na saída.

---

## **2. Comparação Entre Kotlin e Java**

### **Kotlin:**
O código Kotlin utiliza ferramentas para evitar erros relacionados a `null`, como `?.`, `!!`, e `let`.

#### **Tratamento Kotlin:**
```kotlin
val str: String? = "This isn't null"
str?.let { printText(it) } // Chama a função apenas se str não for null

val str4: String? = null
val anotherStr = "This isn't nullable"
println(str4 == anotherStr) // null-safe comparison, retorna false

val str2 = str!! // Força a não-nullability
val str3 = str2.toUpperCase() // Sem erro, já que str não é null
```

#### **Ferramentas Kotlin:**
1. **`?.`:** Chama métodos apenas se a variável não for `null`.
2. **`let`:** Executa um bloco de código apenas se a variável não for `null`.
3. **`!!`:** Força o acesso à variável como não nulo (pode causar `NullPointerException` se usada incorretamente).
4. **`==`:** Operador `null-safe` para comparação.

---

### **Java:**
O mesmo comportamento teria que ser tratado manualmente no Java.

#### **Código Java Equivalente:**
```java
public class NullReferences {

    public static void main(String[] args) {
        String str = "This isn't null";
        if (str != null) {
            printText(str);
        }

        String str4 = null;
        String anotherStr = "This isn't nullable";
        System.out.println(anotherStr.equals(str4)); // Deve verificar null manualmente

        if (str != null) {
            String str2 = str;
            String str3 = str2.toUpperCase();
        }
    }

    public static void printText(String text) {
        System.out.println(text);
    }
}
```

#### **Diferenças no Java:**
1. **Verificações Manuais:**
   - Você precisa verificar `null` manualmente com `if` antes de acessar métodos.
   - Caso contrário, o código lança um **`NullPointerException`**.

2. **Comparação com `null`:**
   - O operador `==` em Java compara referências. Para valores nulos, é necessário usar `equals()` e verificar `null` manualmente:
     ```java
     if (anotherStr != null && anotherStr.equals(str4)) {
         // ...
     }
     ```

---

## **3. Vantagens do Kotlin Sobre Java**

### **Kotlin:**
1. **Null Safety Integrada:**
   - Ferramentas como `?.`, `!!`, `?:`, e `let` ajudam a tratar `null` diretamente no nível da linguagem, reduzindo riscos de erros.
   
2. **Código Mais Conciso:**
   - Kotlin elimina a necessidade de verificações manuais e redundantes.

3. **Operador Seguro `==`:**
   - Em Kotlin, o operador `==` compara valores de forma segura, mesmo se um deles for `null`.

4. **Legibilidade e Simplicidade:**
   - O código é mais curto e fácil de entender.

---

### **Java:**
1. **Tratamento de `null`:**
   - Sem ferramentas integradas, é necessário tratar `null` manualmente.
   - Isso aumenta o risco de erros como **`NullPointerException`**.

2. **Maior Verbosidade:**
   - Mais código precisa ser escrito para realizar as mesmas tarefas.

---

## **Resumo das Diferenças Kotlin x Java**

| **Aspecto**            | **Kotlin**                                    | **Java**                                       |
|------------------------|-----------------------------------------------|-----------------------------------------------|
| **Null Safety**        | Ferramentas nativas como `?.`, `?:`, e `!!`.  | Tratamento manual com `if` e verificações.    |
| **Comparação**         | `==` é `null-safe`.                           | `==` compara referências, `equals()` é usado manualmente. |
| **Verbosidade**        | Código conciso e legível.                     | Código mais longo e sujeito a erros.          |
| **Segurança**          | Reduz o risco de `NullPointerException`.      | Erros de `null` são comuns sem verificações.  |

---

## **Conclusão**

### **Por Que Kotlin É Melhor Nesse Cenário?**
1. **Segurança:** Null Safety integrada reduz o risco de erros.
2. **Produtividade:** Menos código para o mesmo resultado.
3. **Interoperabilidade:** Mesmo com classes Java (`printText` ou comparações), Kotlin oferece segurança adicional sem alterar o código Java.

# ***2 Difernça entre os exemplos usando o operador `!!`: 
a. Correto: 
```` package academy.learnprogramming.nullreferences 

fun main(args: Array<String>) {

    val str: String? = "This isn't null"
    str?.let { printText(it) }

    val str4 : String? = null
    val anotherStr = "This isn't nullable"
    println(str4 == anotherStr)

    val str2 = str!!
    val str3 = str2.toUpperCase()

}

fun printText(text: String) {
    println(text)
}
````
b. Errado: 
````
 package academy.learnprogramming.nullreferences 

fun main(args: Array<String>) {

    val str: String? = null
    val str4 = str!!.toUpperCase()


    println("What happens when we do this: ${str?.toUpperCase()}")

    val str2 = str ?: "This is the default value"
    println(str2)

    //val whatever = bankBranch?.address?.country ?: "US"

    val something: Any = arrayOf(1, 2, 3, 4)
    val str3 = something as? String
    println(str3)

    println(str3?.toUpperCase())
} 
````

### **Parte 1: Como Seria o Código Kotlin em Java e Por que É Mais Verboso**

O código Kotlin fornecido:

```kotlin
package academy.learnprogramming.nullreferences

fun main(args: Array<String>) {
    val str: String? = "This isn't null"
    str?.let { printText(it) }

    val str4: String? = null
    val anotherStr = "This isn't nullable"
    println(str4 == anotherStr)

    val str2 = str!!
    val str3 = str2.toUpperCase()
}

fun printText(text: String) {
    println(text)
}
```

Em Java, seria algo como:

```java
package academy.learnprogramming.nullreferences;

public class NullReferences {
    public static void main(String[] args) {
        // Variável inicializada com um valor não nulo
        String str = "This isn't null";
        
        // Verificação manual para evitar NullPointerException
        if (str != null) {
            printText(str); // Chama o método apenas se str não for null
        }

        // Comparação null-safe (verificação manual de null)
        String str4 = null;
        String anotherStr = "This isn't nullable";
        System.out.println(str4 != null && str4.equals(anotherStr)); // Compara com null manualmente

        // Acessando métodos sem verificar null (gera erro se str for null)
        if (str != null) {
            String str2 = str;
            String str3 = str2.toUpperCase();
        }
    }

    public static void printText(String text) {
        System.out.println(text);
    }
}
```

---

#### **Por que o código Java é mais verboso?**

1. **Verificações de Null:**
   - Em Kotlin:
     ```kotlin
     str?.let { printText(it) }
     ```
     - Usa o operador **`?.`** para verificar `null` automaticamente.
   - Em Java:
     ```java
     if (str != null) {
         printText(str);
     }
     ```
     - Você precisa verificar `null` manualmente com `if`.

2. **Comparações de String:**
   - Em Kotlin:
     ```kotlin
     println(str4 == anotherStr)
     ```
     - O operador `==` em Kotlin é **null-safe**.
   - Em Java:
     ```java
     System.out.println(str4 != null && str4.equals(anotherStr));
     ```
     - Em Java, você precisa verificar manualmente se `str4` é `null` antes de usar `.equals()`.

3. **Acessos Seguros a Métodos:**
   - Em Kotlin:
     ```kotlin
     val str3 = str!!.toUpperCase()
     ```
     - Usa o operador `!!` para forçar o acesso, mas você pode usar `?.` para maior segurança.
   - Em Java:
     ```java
     if (str != null) {
         String str3 = str.toUpperCase();
     }
     ```
     - Em Java, você precisa verificar manualmente antes de acessar qualquer método.

---

### **Parte 2: Diferença no Uso de `!!` nos Dois Exemplos**

O operador **`!!`** força o Kotlin a tratar uma variável como **não nula**, mesmo que ela seja declarada como `nullable` (`String?`). Se a variável for `null`, o programa lança uma **`NullPointerException`**.

#### **a. Código Kotlin (Exemplo A):**
```kotlin
val str: String? = "This isn't null"
val str2 = str!!
val str3 = str2.toUpperCase()
```

##### **Comportamento:**
1. **`str!!`:**
   - Aqui, `str` contém `"This isn't null"`, então o operador `!!` funciona sem problemas.
   - `str2` recebe o valor `"This isn't null"`.

2. **Sem Erros:**
   - Como `str` não é `null`, o programa executa normalmente.
   - A chamada `str2.toUpperCase()` resulta em `"THIS ISN'T NULL"`.

##### **Por que usar `!!` aqui?**
- Não há risco, porque o desenvolvedor garante que `str` nunca será `null`.

---

#### **b. Código Kotlin (Exemplo B):**
```kotlin
val str: String? = null
val str4 = str!!.toUpperCase()
```

##### **Comportamento:**
1. **`str!!`:**
   - Aqui, `str` é **`null`**. O operador `!!` força o Kotlin a tratar `str` como não nulo.
   - Quando o método `toUpperCase()` é chamado, o programa lança uma **`NullPointerException`**.

2. **Erro Intencional:**
   - O uso de `!!` aqui é imprudente, porque `str` pode ser `null`.
   - O erro demonstra o que acontece quando `!!` é usado sem verificar o valor.

##### **Por que o erro acontece?**
- O operador `!!` foi usado em uma variável `null`, violando as proteções de `null safety` do Kotlin.

---

### **Diferença Entre os Dois Exemplos**

| **Aspecto**                | **Exemplo A**                                      | **Exemplo B**                                      |
|----------------------------|---------------------------------------------------|---------------------------------------------------|
| **Valor de `str`**         | `"This isn't null"`                                | `null`                                            |
| **Uso de `!!`**            | Seguro, pois `str` não é `null`.                   | Inseguro, pois `str` é `null`.                    |
| **Resultado**              | Nenhum erro, resultado é `"THIS ISN'T NULL"`.      | Lança `NullPointerException`.                     |
| **Propósito**              | Demonstra o uso seguro de `!!`.                    | Mostra o risco de usar `!!` com valores nulos.    |

---

### **Resumo da Comparação Kotlin vs Java**

| **Aspecto**            | **Kotlin**                                     | **Java**                                      |
|------------------------|-----------------------------------------------|----------------------------------------------|
| **Null Safety**        | Ferramentas nativas como `?.`, `!!`, `?:`.    | Precisa de verificações manuais com `if`.    |
| **Verbosidade**        | Código mais conciso e legível.                | Código mais longo e sujeito a erros.         |
| **Comparação de Strings** | `==` é null-safe.                          | Precisa de verificações explícitas.          |
| **Tratamento de `null`**  | Ferramentas automáticas para evitar erros. | Exige verificações manuais e é propenso a falhas. |

### **Conclusão**
- O operador **`!!`** deve ser usado com cuidado, pois viola a segurança contra `null` do Kotlin.
- Kotlin simplifica o tratamento de `null` e reduz a verbosidade em comparação com Java, oferecendo ferramentas como `?.`, `?:` e `let`.
- Em Java, seria necessário verificar `null` manualmente, resultando em código mais longo e suscetível a erros.
