# DSA-Project
This project is a Markov Text Generator implemented in Java. It uses Markov chains to predict the next word based on the previous word, allowing for the generation of random text that follows the statistical properties of the input text.
## Table of Contents
1. [Introduction](#introduction)
2. [Project Overview](#project-overview)
3. [Installation and Setup](#installation-and-setup)
4. [Usage](#usage)
5. [Features](#features)
6. [Folder Structure](#folder-structure)
7. [Contributing](#contributing)
8. [License](#license)
9. [Contact Information](#contact-information)
10. [Acknowledgements](#acknowledgements)

## Project Overview
The Markov Text Generator project includes:
- **MarkovTextGenerator Interface**: Defines the core methods for training and generating text.
- **ListNode Class**: Represents each word and its possible next words.
- **MarkovTextGeneratorLoL Class**: Implements the interface and manages the list of nodes.

## Installation and Setup
To set up the project on your local machine, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/MarkovTextGenerator.git
    ```
2. Navigate to the project directory:
    ```bash
    cd MarkovTextGenerator
    ```

## Usage
Compile and run the `MarkovTextGeneratorLoL` class. Ensure you have the required Java environment set up on your machine.

```bash
javac src/prototype/MarkovTextGeneratorLoL.java
java src/prototype/MarkovTextGeneratorLoL
