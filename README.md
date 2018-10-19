# missile-track

## Usage
```
$ http 35.193.131.103:8080/missile
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Fri, 28 Sep 2018 13:38:50 GMT
Transfer-Encoding: chunked
{
    "outOfRange": false,
    "xLocation": 0.0,
    "yLocation": 0.0,
    "zLocation": 0.0
}

$ http POST 35.193.131.103:8080/missile outOfRange=true
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Fri, 28 Sep 2018 13:38:50 GMT
Transfer-Encoding: chunked
{
    "outOfRange": true,
    "xLocation": 0.0,
    "yLocation": 0.0,
    "zLocation": 0.0
}

$ http POST localhost:8080/estimate launchPointId=L1 impactPointId=T1

```

## Deploying to GKE
```
 mvn package -B
 docker build -t gcr.io/uengine-istio-test/missile-track:v1 .
 docker push gcr.io/uengine-istio-test/missile-track:v1
 kubectl run missile --image=gcr.io/uengine-istio-test/missile-track:v1
 kubectl expose deploy/missile --type=LoadBalancer --port=8080
 kubectl get svc -w
```

## Rolling update to GKE
```
 git pull
 mvn package -B
 docker build -t gcr.io/uengine-istio-test/missile-track:v2 .
 docker push gcr.io/uengine-istio-test/missile-track:v2
 kubectl set image deploy/missile missile=gcr.io/uengine-istio-test/missile-track:v2
```

## Test
```
 http localhost:8080/missile impactPointId="T8" launchPointId="L2"
  596  http localhost:8080/engageFeasible
  599  http localhost:8080/dba
  600  http localhost:8080/missile impactPointId="T8" launchPointId="L3"
  601  http localhost:8080/dba
  602  http localhost:8080/engageFeasible
  603  http localhost:8080/missile impactPointId="T8" launchPointId="L3"
  605  http localhost:8080/missile impactPointId="T8" launchPointId="L2"
  606  http localhost:8080/dba
```