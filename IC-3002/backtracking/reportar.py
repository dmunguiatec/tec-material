def reportar(tablero):
    """
    This function prints a 2D chessboard based on an input array.
    The input array indicates the occupied column in each row.
    """
    # Determine the size of the chessboard
    n = len(tablero)

    # Iterate over each row
    for i in range(n):
        row = ""
        # Iterate over each column
        for j in range(n):
            # If the current column is the one indicated in tablero, mark it as occupied
            if tablero[i] == j:
                row += "X"
            else:
                row += "."
        # Print the entire row
        print(row)


# Example usage
# Given chessboard where the occupied column for each row is represented by the index in 'tablero'
tablero = [1, 3, 0, 2]  # This would be a 4x4 board with specific occupied positions

# Call the reportar function with the example chessboard
reportar(tablero)

