# ed2;rcelo

Repositório do trabalho de Estrutura de Dados (DCC012) do Depto de Ciência da Computação da UFJF referente ao semestre 2020.1.

## Book Depository Dataset :closed_book:

O conjunto de dados (registros) em *.csv* está disponível em [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). É necessário fazer o *download* do arquivo para executar o programa e deixá-lo na pasta do projeto para poder rodá-lo.

**Observação**: Para rodar o programa, além do *DataSet* estar no diretório, o arquivo de entrada especificado na descrição do trabalho também deve estar, *entrada.txt*. Para rodar via terminal no Linux basta baixar o Trabalho 01, 02 ou 03 do repositório e executar o comando:

```java -jar Trabalho01.jar```, ```java -jar Trabalho02.jar``` ou ```java -jar Trabalho03.jar```.

### Parte I

#### Análise dos Algoritmos de Ordenação :chart_with_upwards_trend:
- [x] Armazenar os registros em um vetor de tamanho N (*authors [list], bestsellers-rank [int], categories [list], edition [str], id [int], isbn10 [str], isbn13 [str], rating-avg [int], rating-count [int], title [str]*). 
- [x] Contabilizar o número de comparações de chaves;
- [x] Contabilizar o número de cópias de registros;
- [x] Contabilizar o tempo total gasto na ordenação (tempo de máquina, não de relógio).

##### Escolha dos algoritmos :bookmark_tabs:

* QuickSort
* HeapSort

**Observação:** O programa deve receber um arquivo de entrada (*entrada.txt*) com o formato: *N* é número de valores que se seguem, um por linha. Exemplo: *N = 3*, então cada linha terá um valor especificado de 1000 na primeira linha, 5000 na segunda e 10000 na terceira.

Para cada valor de *N* lido do arquivo *entrada.txt*:
- [x] Gera cada um dos conjuntos dos elementos, ordena, contabiliza estatísticas de desempenho.
- [x] Armazena estatísticas de desempenho em arquivo de saída (*saida.txt*).

### Parte II
#### Implementação dos Autores mais Frequentes :bar_chart:

### Parte III
#### Busca em Estruturas Balanceadas e Auto-Ajustáveis :bookmark:


