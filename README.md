# movie-booking
On-line Movie Booking 

`POST /admin/screens`
```
{
    "name": "Audi 1"
}
```

`POST /admin/seats`
```
{
    "rowNo": "A",
    "seatNo": 1
}
```

`POST /admin/theaters`
```
{
    "name": "Audi 1"
}
```

`POST /admin/movies`
```
{
    "name": "Sultan"
}
```

`POST /admin/shows`
```
{
    "movieId": <movieId>,
    "screenId": <screenId>,
    "showTime": "2021-11-10 09:00:00",
    "durationInMinutes": 120
}
```

`PUT /admin/screens/<screenId>?seats=1,2,3,4,5`

`PUT /admin/theaters/<theaterId>?screens=1`

`GET /admin/seats/all`

`GET /admin/screens/<screenId>`

`GET /admin/theaters/<theaterId>`

`GET /admin/shows/<showId>`