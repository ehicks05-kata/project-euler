module Eric.Util 

let isPrime (n: int64) =
    match n with
    | n when n < 2L -> false
    | n ->
        let divisors =
            let limit = int64 (sqrt (float n))
            seq {2L .. limit}
        not <| Seq.exists (fun d -> n % d = 0L) divisors
        
let isFactor (n: int64) (f: int64) =
    match f with
        | f when n % f = 0L -> true
        | _ -> false
        
let prettyPrintNumber (x : int64) =
    System.String.Format("{0:N0}", x)