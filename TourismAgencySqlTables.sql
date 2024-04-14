CREATE TABLE IF NOT EXISTS public.user
(
    user_id SERIAL,
    user_role text NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS public.hotel
(
    hotel_id SERIAL,
    hotel_name text NOT NULL,
    hotel_city text NOT NULL,
    hotel_address text NOT NULL,
    hotel_email text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL,
    ultra_pension boolean NOT NULL,
    all_inclusive_pension boolean NOT NULL,
    room_breakfast_pension boolean NOT NULL,
    full_pension boolean NOT NULL,
    half_pension boolean NOT NULL,
    only_bed_pension boolean NOT NULL,
    full_credit_pension boolean NOT NULL,
    PRIMARY KEY (hotel_id)
);

CREATE TABLE IF NOT EXISTS public.pension
(
    pension_id SERIAL,
    pension_hotel_id int NOT NULL,
    pension_type text NOT NULL,
    PRIMARY KEY (pension_id),
    FOREIGN KEY (pension_hotel_id) REFERENCES public.hotel(hotel_id)
);

CREATE TABLE IF NOT EXISTS public.season
(
    season_id SERIAL,
    season_hotel_id int NOT NULL,
    season_start_date date NOT NUll,
    season_end_day date NOT NULL,
    PRIMARY KEY (season_id),
    FOREIGN KEY ( season_hotel_id) REFERENCES public.hotel(hotel_id)
);

CREATE TABLE IF NOT EXISTS public.room
(
    room_id SERIAL,
    room_hotel_id int NOT NULL,
    room_type text NOT NULL,
    room_beds int NOT NULL,
    room_area int NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    safe boolean NOT NULL,
    stock int NOT NULL,
    PRIMARY KEY (room_id),
    FOREIGN KEY (room_hotel_id) REFERENCES public.hotel(hotel_id)
);

CREATE TABLE IF NOT EXISTS public.price
(
    price_id SERIAL,
    price_room_id int NOT NULL,
    price_hotel_id int NOT NULL,
    price_pension_id int NOT NULL,
    price_season_id int NOT NULL,
    price_guest_type text NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (price_id),
    FOREIGN KEY (price_room_id) REFERENCES public.room(room_id),
    FOREIGN KEY (price_hotel_id) REFERENCES public.hotel(hotel_id),
    FOREIGN KEY (price_pension_id) REFERENCES public.pension(pension_id),
    FOREIGN KEY (price_season_id) REFERENCES public.season(season_id)
);

CREATE TABLE IF NOT EXISTS public.reservation
(
    reservation_id SERIAL,
    reservation_room_id int NOT NULL,
    reservation_hotel_id int NOT NULL,
    reservation_pension_id int NOT NULL,
    reservation_season_id int NOT NULL,
    customer_name text NOT NULL,
    customer_id text NOT NULL,
    adults int NOT NULL,
    children int NOT NULL,
    reservation_start_date date NOT NULL,
    reservation_end_date date NOT NULL,
    cost float NOT NULL,
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (reservation_room_id) REFERENCES public.room(room_id),
    FOREIGN KEY (reservation_hotel_id) REFERENCES public.hotel(hotel_id),
    FOREIGN KEY (reservation_pension_id) REFERENCES public.pension(pension_id),
    FOREIGN KEY (reservation_season_id) REFERENCES public.season(season_id)
);