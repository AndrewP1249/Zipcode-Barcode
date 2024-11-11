# Zipcode-Barcode Project

This project is a Java program that encodes and decodes a 5-digit ZIP code into a postal barcode format, following the U.S. Postal Service's encoding scheme for faster sorting of letters. Each digit of the ZIP code is represented by a series of bars, where a **half bar** represents 0 and a **full bar** represents 1. The barcode also includes a **check digit** to make the sum of the ZIP code digits a multiple of 10.

## Overview

In this encoding scheme:
- A 5-digit ZIP code is represented by a sequence of encoded bars.
- The encoding for each digit follows a specific pattern, with each digit corresponding to a unique combination of bars.

The program includes two main functionalities:
1. **Encoding**: Converts a 5-digit ZIP code into a barcode format.
2. **Decoding**: Translates a barcode back into a ZIP code, validating the check digit and reporting any errors in the input format or mismatches in the digits.

## Encoding Scheme
The encoding scheme assigns a unique pattern of full and half bars to each digit, following the weight values shown in the table (note: the only exception is zero which yields 11 on the weight table):
- **Weights**: 7, 4, 2, 1, 0

For example:
The digit "6" would be represented as 01100, which corresponds to the weights:
0 × 7 + 1 × 4 + 1 × 2 + 0 × 1 + 0 × 0 = 6

### Encoding Table
| Digit | Barcode Pattern | Calculation              |
|-------|-----------------|--------------------------|
| 0     | 11000           | 7 + 4 + 0 + 0 + 0 = 11   |
| 1     | 00011           | 0 + 0 + 0 + 1 + 0 = 1    |
| 2     | 00101           | 0 + 0 + 2 + 0 + 0 = 2    |
| 3     | 00110           | 0 + 0 + 2 + 1 + 0 = 3    |
| 4     | 01001           | 0 + 4 + 0 + 0 + 0 = 4    |
| 5     | 01010           | 0 + 4 + 0 + 1 + 0 = 5    |
| 6     | 01100           | 0 + 4 + 2 + 0 + 0 = 6    |
| 7     | 10001           | 7 + 0 + 0 + 0 + 0 = 7    |
| 8     | 10010           | 7 + 0 + 0 + 1 + 0 = 8    |
| 9     | 10100           | 7 + 0 + 2 + 0 + 0 = 9    |
