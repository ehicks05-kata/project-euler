open System

// The prime factors of 13195 are 5, 7, 13 and 29.
// 
// What is the largest prime factor of the number 600851475143 ?

let input = 600_851_475_143L

let isPrime (n: int64) =
    match n with
    | n when n < 2L -> false
    | n ->
        let divisors = seq {2L .. int64 (sqrt (float n))}
        not <| Seq.exists (fun d -> n % d = 0L) divisors
        
let isFactor (n: int64) (f: int64) =
    match f with
        | f when n % f = 0L -> true
        | _ -> false

let isFactorOfTarget =
    isFactor input   
    
let printInfo factor =
    let pair = input / factor
    let isPairPrime =
        match pair with
            | pair when isPrime pair -> "Prime"
            | _ -> "Not Prime"
    printfn "%d (Prime) * %d %s = %d" factor pair isPairPrime input
    factor

let printNumber (x : int64) : string =
    System.String.Format("{0:N0}",x)
    
let printEveryXth (interval: int64) (x: int64) =
    match x with
        | x when x % interval = 0L -> printfn "%s" (printNumber x)
        | _ -> ()
    x
   
let max =
    float input
    |> Math.Sqrt
    |> Math.Round
    |> int64
    
printfn "testing 1 to %d" max
    
let mySeq = seq {1L .. max}

let answer =
    mySeq
    |> Seq.map (printEveryXth 1_000_000L)
    |> Seq.filter isPrime
    |> Seq.filter isFactorOfTarget
    |> Seq.map printInfo
    |> Seq.last
    
printfn "answer: %d" answer