# Prática 05 – TDD em Java: Reflexão

## 1. No Ciclo 1 usamos `return 70`. Por que isso é aceitável no TDD? O que obrigou a escrever a soma de verdade?

No TDD o objetivo da fase verde é escrever o mínimo de código para o teste passar, e `return 70` faz exatamente isso (técnica *fake it*). Com um único exemplo, não há nada nos testes que exija mais do que um valor fixo. O que obrigou a escrever a soma de verdade foi o segundo teste do Ciclo 2 (`calcularNotaFinal(10, 15, 25)` deve dar 50): ele falhou com `return 70`, e a única forma de fazer os dois passarem ao mesmo tempo foi implementar `a1 + a2 + a3`. Isso é a triangulação.

## 2. No Ciclo 3, por que testar com 69 e 70, e não com 50 e 90?

Porque o erro mais provável está na fronteira da regra: escrever `> 70` em vez de `>= 70`. Com 50 e 90 os dois operadores dão o mesmo resultado, então o teste nunca pegaria o erro. Com 70 (deve aprovar) e 69 (deve reprovar), qualquer erro no limite faz um teste falhar. Isso é análise de valor limite.

## 3. No Ciclo 4, o que mudou na refatoração? Como você teve certeza de que não quebrou nada?

Os três `if` quase iguais que validavam A1, A2 e A3 viraram um único método privado, `validarNota(nota, notaMaxima, nomeAvaliacao)`, e os limites 30 e 40 viraram constantes. O comportamento continuou o mesmo, só a estrutura do código mudou. A certeza veio dos testes: rodei todos depois da refatoração e eles continuaram verdes, inclusive os cinco testes de notas inválidas e de notas máximas do próprio Ciclo 4.

## 4. No Ciclo 8, por que alteramos os testes antes de mudar o código?

Porque no TDD os testes são a especificação. Quando o requisito muda, primeiro os testes passam a descrever a regra nova (e falham, porque o código ainda segue a regra antiga). Só depois o código muda para atender. Assim fica provado que os testes realmente cobrem a regra nova, e a mudança no código vai só até onde os testes pedem.

## 5. Qual ciclo foi o mais difícil para você, e por quê?

O Ciclo 7 (aplicar a nota da AI). Além de escrever os testes sozinho, tive que pensar em vários casos diferentes: a AI substituindo a A1, substituindo a A2, uma AI baixa que não substitui nada e uma AI fora da faixa. Também precisei decidir o que acontece quando a AI é igual à menor nota, e reaproveitar a validação que já existia em vez de duplicar código.
