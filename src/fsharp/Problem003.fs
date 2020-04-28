module ClassLibrary1.Problem003

open System
open Eric.Util

// The prime factors of 13195 are 5, 7, 13 and 29.
// 
// What is the largest prime factor of the number 600851475143 ?

let input = 600_851_475_143L
    
let printInfo factor =
    let pair = input / factor
    let isPairPrime =
        match pair with
            | pair when isPrime pair -> "Prime"
            | _ -> "Not Prime"
    printfn "%d (Prime) * %d %s = %d" factor pair isPairPrime input
    factor
    
let max =
    float input |> Math.Sqrt |> Math.Round |> int64
       
let mySeq = seq {1L .. max}

let answer =
    mySeq
    |> Seq.filter isPrime
    |> Seq.filter (isFactor input)
    |> Seq.map printInfo
    |> Seq.last
    
printfn "answer: %d" answer