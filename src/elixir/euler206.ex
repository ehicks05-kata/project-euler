defmodule Euler206 do
  @moduledoc """
  Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
  where each "_" is a single digit.
  """

  # single thread
  def solve() do
    regex = ~r/1\d2\d3\d4\d5\d6\d7\d8\d9\d0/
    from = round(:math.sqrt(1_020_304_050_607_080_900))
    to = round(:math.sqrt(1_929_394_959_697_989_990))

    Stream.filter(from..to, &(rem(&1, 10) === 0 and Enum.member?([3, 7], List.last(Integer.digits(div(&1, 10))))))
    #    |> Stream.each(&(IO.puts(&1)))
    |> Enum.find(nil, &Regex.match?(regex, Integer.to_string(&1 * &1)))
    |> IO.inspect
  end

  # parallel
  def solve2() do
    from = round(:math.sqrt(1_020_304_050_607_080_900))
    to = round(:math.sqrt(1_929_394_959_697_989_990))
    range = to - from
    chunks = 8
    chunk_size = div(range, chunks)

    0..(chunks - 1)
    |> Enum.map(&((from + &1 * chunk_size)..(from + (&1 + 1) * chunk_size)))
    |> Task.async_stream(Euler206, :check_match, [], timeout: 120_000)
    |> Enum.filter(fn {_, b} -> b !== nil end)
    |> IO.inspect
  end

  # process that receives a chunk of work
  def match_checker(from..to) do
    receive do
      senders_pid ->
        send(senders_pid, check_match(from..to))
    end
  end

  # does the actual checking
  def check_match(from..to) do
    regex = ~r/1\d2\d3\d4\d5\d6\d7\d8\d9\d0/
    IO.inspect(from..to)

    Stream.filter(from..to, &(rem(&1, 10) === 0 and Enum.member?([3, 7], List.last(Integer.digits(div(&1, 10))))))
    |> Enum.find(nil, &Regex.match?(regex, Integer.to_string(&1 * &1)))
  end
end

Benchee.run(%{
  "solution 1" => fn -> Euler206.solve() end,
  "solution 2" => fn -> Euler206.solve2() end
})
