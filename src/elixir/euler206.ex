defmodule Euler206 do
  @moduledoc """
  Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
  where each "_" is a single digit.
  """

  def solve() do
    regex = ~r/1\d2\d3\d4\d5\d6\d7\d8\d9\d0/
    from = round(:math.sqrt(1020304050607080900))
    to =   round(:math.sqrt(1929394959697989990))

    Stream.filter(from..to, &(rem(&1, 10) === 0))
    #    |> Stream.each(&(IO.puts(&1)))
    |> Enum.find(nil, &(Regex.match?(regex, Integer.to_string(&1 * &1))))

  end
end