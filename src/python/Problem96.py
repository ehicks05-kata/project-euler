import copy
import time

verbose_log = False


def main():
    start = time.time()
    file = open('../data/sudoku1.txt')
    lines = file.read().split("\n")

    answer = 0

    grid = []
    current_grid = 1
    for line in lines:
        if line.startswith("Grid"):
            continue

        if len(grid) < 9:
            row = [int(cell) for cell in line]
            grid.append(row)

        if len(grid) == 9:
            print("\nGrid " + str(current_grid) + ":")

            print_grid(grid)
            determine_possible_values(grid)
            if verbose_log:
                print_grid(grid)
            solve_trivial(grid)
            if verbose_log:
                print_grid(grid)

            if not is_puzzle_solved(grid):
                grid = solve_advanced(grid)

            print_grid(grid)

            first_three_digits = str(grid[0][0]) + str(grid[0][1]) + str(grid[0][2])
            print(str(answer) + " + " + first_three_digits + " = " + str(answer + int(first_three_digits)))
            answer += int(first_three_digits)
            grid = []
            current_grid += 1

    print("\n" + str(answer))
    print("\ntook " + str(time.time() - start) + " seconds")


def solve_advanced(grid):
    answer = solve_recursive(grid, False)
    return answer[0]


def solve_recursive(grid, solved):
    first_unsolved_cell = get_first_unsolved_cell(grid)
    if first_unsolved_cell is None:
        # print_grid(grid)
        return grid, True

    row, col = first_unsolved_cell[0], first_unsolved_cell[1]
    for value in grid[row][col]:
        work_grid = copy.deepcopy(grid)
        work_grid[row][col] = value
        cascade_cell_solution(work_grid, row, col)
        solve_trivial(work_grid)

        if is_grid_valid(work_grid):
            _, solved = solve_recursive(work_grid, False)
            if solved:
                return _, solved

    return grid, False


def is_grid_valid(grid):
    for row in range(len(grid)):
        for col in range(len(grid[row])):
            value = grid[row][col]

            if not value:
                return False

            related_cells = get_related_cells(row, col)
            related_cells.remove((row, col))
            for r, c in related_cells:
                rc_value = grid[r][c]
                if type(rc_value) is int and rc_value == value:
                    return False
    return True


def get_first_unsolved_cell(grid):
    for row in range(len(grid)):
        for col in range(len(grid[row])):
            if type(grid[row][col]) is not int:
                return row, col
    return None


def get_unsolved_cells(grid):
    unsolved_cells = 0
    for row in range(len(grid)):
        for col in range(len(grid[row])):
            if type(grid[row][col]) is not int:
                unsolved_cells += 1
    return unsolved_cells


def get_box_coordinates(row, col):
    start_row = (row // 3) * 3
    start_col = (col // 3) * 3
    return start_row, start_row + 3, start_col, start_col + 3


def is_puzzle_solved(grid):
    return get_unsolved_cells(grid) == 0


def solve_trivial(grid):
    solved_a_cell = True
    while solved_a_cell:
        solved_a_cell = solve_trivial_one_loop(grid)


def solve_trivial_one_loop(grid):
    for row in range(9):
        for col in range(9):
            cell_value = grid[row][col]
            if type(cell_value) == set:
                # if the length of the set is 1, replace it with that one value
                if len(cell_value) == 1:
                    if verbose_log:
                        print("replacing " + str(cell_value) + " with " + str(list(cell_value)[0]) +
                              " @ position (" + str(row) + ", " + str(col) + ")")
                    cell_value = list(cell_value)[0]
                    grid[row][col] = cell_value
                    cascade_cell_solution(grid, row, col)
                    return True

                # determine how many times each number in the set appears in the box, row, and col
                for value in cell_value:
                    if is_unique_within_box_row_or_col(grid, row, col, value):
                        if verbose_log:
                            print("replacing " + str(cell_value) + " with " + str(value) +
                                  " @ position (" + str(row) + ", " + str(col) + ")")
                        grid[row][col] = value
                        cascade_cell_solution(grid, row, col)
                        return True

    return False


def is_unique_within_box_row_or_col(grid, row, col, val):
    box_coordinates = get_box_coordinates(row, col)
    cells_in_box = get_cells(*box_coordinates)
    cells_in_row = get_cells(row, row + 1, 0, 9)
    cells_in_col = get_cells(0, 9, col, col + 1)

    values_in_box = get_values_from_cells(grid, cells_in_box)
    values_in_row = get_values_from_cells(grid, cells_in_row)
    values_in_col = get_values_from_cells(grid, cells_in_col)

    # if our value is unique within its box, row, or col, then it must be the right number
    return values_in_box.count(val) < 2 or values_in_row.count(val) < 2 or values_in_col.count(val) < 2


def cascade_cell_solution(grid, row, col):
    solved_cell_value = grid[row][col]
    related_cells = get_related_cells(row, col)
    for row, col in related_cells:
        cell_value = grid[row][col]
        if type(cell_value) == set and solved_cell_value in cell_value:
            new_cell_value = set(cell_value)
            new_cell_value.remove(solved_cell_value)
            if verbose_log:
                print("  replacing " + str(cell_value) + " with " + str(new_cell_value) +
                      " @ position (" + str(row) + ", " + str(col) + ")")
            cell_value.remove(solved_cell_value)


def count_occurrences(grid, row, col, value):
    related_cells = get_related_cells(row, col)

    all_relevant_values = get_values_from_cells(grid, related_cells)

    return all_relevant_values.count(value)


def get_related_cells(row, col):
    box_coordinates = get_box_coordinates(row, col)
    cells_in_box = get_cells(*box_coordinates)
    cells_in_row = get_cells(row, row + 1, 0, 9)
    cells_in_col = get_cells(0, 9, col, col + 1)
    return set(cells_in_box | cells_in_row | cells_in_col)


def get_cells(from_row, to_row, from_col, to_col):
    cells = set()
    for row in range(from_row, to_row):
        for col in range(from_col, to_col):
            cells.add((row, col))
    return cells


def get_values_from_cells(grid, cells):
    values = []
    for row, col in cells:
        cell_value = grid[row][col]
        if type(cell_value) is int:
            values.append(cell_value)
        if type(cell_value) is set:
            for value in cell_value:
                values.append(value)
    return values


def determine_possible_values(grid):
    for row in range(9):
        for col in range(9):
            if grid[row][col] == 0:
                possible_values = set(range(1, 10))
                grid[row][col] = possible_values
                remove_illegal_values(grid, row, col)


def remove_illegal_values(grid, row, col):
    value = grid[row][col]
    if type(value) is int:
        return

    box_coordinates = get_box_coordinates(row, col)
    values_in_box = get_known_values(grid, *box_coordinates)
    values_in_row = get_known_values(grid, row, row + 1, 0, 9)
    values_in_col = get_known_values(grid, 0, 9, col, col + 1)

    possible_values = value - (values_in_box | values_in_row | values_in_col)
    grid[row][col] = possible_values


def get_known_values(grid, from_row, to_row, from_col, to_col):
    values = set()
    for row in range(from_row, to_row):
        for col in range(from_col, to_col):
            value = grid[row][col]
            if type(value) is int and value > 0:
                values.add(value)

    return values


def print_grid(grid):
    col_widths = [0] * 9
    for row in range(9):
        for col in range(9):
            cell_value = grid[row][col]
            length = len(str(cell_value))
            if length > col_widths[col]:
                col_widths[col] = length

    string = "\n"
    for row in range(9):

        if row % 3 == 0:
            vertical_bars_width = 6
            width = sum(col_widths) + vertical_bars_width + 9
            string += "-" * width + "\n"

        row_string = ""
        for col in range(9):
            print_width = col_widths[col]
            cell_value = grid[row][col]

            if type(cell_value) is set:
                cell_value = sorted(list(cell_value))

            if col % 3 == 0:
                row_string += "| "

            row_string += "{0:^{1}}".format(str(cell_value), print_width + 1)
        string += row_string + "\n"

    print(string)


if __name__ == '__main__':
    main()
