PGDMP  /    7                |            tourismagency    15.6    16.2 @    A           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            B           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            C           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            D           1262    17110    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    17579    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
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
    full_credit_pension boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    17578    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    217            E           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    216            �            1259    17588    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    pension_hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    17587    pension_pension_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_pension_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pension_pension_id_seq;
       public          postgres    false    219            F           0    0    pension_pension_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.pension_pension_id_seq OWNED BY public.pension.pension_id;
          public          postgres    false    218            �            1259    17628    price    TABLE       CREATE TABLE public.price (
    price_id integer NOT NULL,
    price_room_id integer NOT NULL,
    price_hotel_id integer NOT NULL,
    price_pension_id integer NOT NULL,
    price_season_id integer NOT NULL,
    price_guest_type text NOT NULL,
    price double precision NOT NULL
);
    DROP TABLE public.price;
       public         heap    postgres    false            �            1259    17627    price_price_id_seq    SEQUENCE     �   CREATE SEQUENCE public.price_price_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.price_price_id_seq;
       public          postgres    false    225            G           0    0    price_price_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.price_price_id_seq OWNED BY public.price.price_id;
          public          postgres    false    224            �            1259    17657    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    reservation_hotel_id integer NOT NULL,
    reservation_pension_id integer NOT NULL,
    reservation_season_id integer NOT NULL,
    customer_name text NOT NULL,
    customer_id text NOT NULL,
    adults integer NOT NULL,
    children integer NOT NULL,
    reservation_start_date date NOT NULL,
    reservation_end_date date NOT NULL,
    cost double precision NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17656    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    227            H           0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    226            �            1259    17614    room    TABLE     n  CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_type text NOT NULL,
    room_beds integer NOT NULL,
    room_area integer NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    safe boolean NOT NULL,
    projection boolean NOT NULL,
    stock integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17613    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    223            I           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    222            �            1259    17602    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_hotel_id integer NOT NULL,
    season_start_date date NOT NULL,
    season_end_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17601    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    221            J           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    220            �            1259    17570    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_role text NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17569    user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    215            K           0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    214            �           2604    17582    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    17591    pension pension_id    DEFAULT     x   ALTER TABLE ONLY public.pension ALTER COLUMN pension_id SET DEFAULT nextval('public.pension_pension_id_seq'::regclass);
 A   ALTER TABLE public.pension ALTER COLUMN pension_id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    17631    price price_id    DEFAULT     p   ALTER TABLE ONLY public.price ALTER COLUMN price_id SET DEFAULT nextval('public.price_price_id_seq'::regclass);
 =   ALTER TABLE public.price ALTER COLUMN price_id DROP DEFAULT;
       public          postgres    false    225    224    225            �           2604    17660    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    226    227    227            �           2604    17617    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    17605    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    17573    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    214    215    215            4          0    17579    hotel 
   TABLE DATA           )  COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_address, hotel_email, hotel_phone, hotel_star, car_park, wifi, pool, fitness, spa, room_service, ultra_pension, all_inclusive_pension, room_breakfast_pension, full_pension, half_pension, only_bed_pension, full_credit_pension) FROM stdin;
    public          postgres    false    217   �Q       6          0    17588    pension 
   TABLE DATA           M   COPY public.pension (pension_id, pension_hotel_id, pension_type) FROM stdin;
    public          postgres    false    219   �R       <          0    17628    price 
   TABLE DATA           �   COPY public.price (price_id, price_room_id, price_hotel_id, price_pension_id, price_season_id, price_guest_type, price) FROM stdin;
    public          postgres    false    225   �S       >          0    17657    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, reservation_room_id, reservation_hotel_id, reservation_pension_id, reservation_season_id, customer_name, customer_id, adults, children, reservation_start_date, reservation_end_date, cost) FROM stdin;
    public          postgres    false    227   }U       :          0    17614    room 
   TABLE DATA           �   COPY public.room (room_id, room_hotel_id, room_type, room_beds, room_area, tv, minibar, game_console, safe, projection, stock) FROM stdin;
    public          postgres    false    223   kV       8          0    17602    season 
   TABLE DATA           `   COPY public.season (season_id, season_hotel_id, season_start_date, season_end_date) FROM stdin;
    public          postgres    false    221   �V       2          0    17570    user 
   TABLE DATA           N   COPY public."user" (user_id, user_role, user_name, user_password) FROM stdin;
    public          postgres    false    215   KW       L           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 5, true);
          public          postgres    false    216            M           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 19, true);
          public          postgres    false    218            N           0    0    price_price_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.price_price_id_seq', 88, true);
          public          postgres    false    224            O           0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 5, true);
          public          postgres    false    226            P           0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 5, true);
          public          postgres    false    222            Q           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 10, true);
          public          postgres    false    220            R           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 3, true);
          public          postgres    false    214            �           2606    17586    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    217            �           2606    17595    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    219            �           2606    17635    price price_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_pkey PRIMARY KEY (price_id);
 :   ALTER TABLE ONLY public.price DROP CONSTRAINT price_pkey;
       public            postgres    false    225            �           2606    17664    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    227            �           2606    17621    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    223            �           2606    17607    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    221            �           2606    17577    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            �           2606    17596 %   pension pension_pension_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pension_hotel_id_fkey FOREIGN KEY (pension_hotel_id) REFERENCES public.hotel(hotel_id);
 O   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pension_hotel_id_fkey;
       public          postgres    false    217    219    3213            �           2606    17641    price price_price_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_price_hotel_id_fkey FOREIGN KEY (price_hotel_id) REFERENCES public.hotel(hotel_id);
 I   ALTER TABLE ONLY public.price DROP CONSTRAINT price_price_hotel_id_fkey;
       public          postgres    false    3213    217    225            �           2606    17646 !   price price_price_pension_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_price_pension_id_fkey FOREIGN KEY (price_pension_id) REFERENCES public.pension(pension_id);
 K   ALTER TABLE ONLY public.price DROP CONSTRAINT price_price_pension_id_fkey;
       public          postgres    false    225    219    3215            �           2606    17636    price price_price_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_price_room_id_fkey FOREIGN KEY (price_room_id) REFERENCES public.room(room_id);
 H   ALTER TABLE ONLY public.price DROP CONSTRAINT price_price_room_id_fkey;
       public          postgres    false    3219    225    223            �           2606    17651     price price_price_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_price_season_id_fkey FOREIGN KEY (price_season_id) REFERENCES public.season(season_id);
 J   ALTER TABLE ONLY public.price DROP CONSTRAINT price_price_season_id_fkey;
       public          postgres    false    225    221    3217            �           2606    17670 1   reservation reservation_reservation_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_reservation_hotel_id_fkey FOREIGN KEY (reservation_hotel_id) REFERENCES public.hotel(hotel_id);
 [   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_reservation_hotel_id_fkey;
       public          postgres    false    227    217    3213            �           2606    17675 3   reservation reservation_reservation_pension_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_reservation_pension_id_fkey FOREIGN KEY (reservation_pension_id) REFERENCES public.pension(pension_id);
 ]   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_reservation_pension_id_fkey;
       public          postgres    false    219    3215    227            �           2606    17665 0   reservation reservation_reservation_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_reservation_room_id_fkey FOREIGN KEY (reservation_room_id) REFERENCES public.room(room_id);
 Z   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_reservation_room_id_fkey;
       public          postgres    false    227    223    3219            �           2606    17680 2   reservation reservation_reservation_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_reservation_season_id_fkey FOREIGN KEY (reservation_season_id) REFERENCES public.season(season_id);
 \   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_reservation_season_id_fkey;
       public          postgres    false    227    3217    221            �           2606    17622    room room_room_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_room_hotel_id_fkey FOREIGN KEY (room_hotel_id) REFERENCES public.hotel(hotel_id);
 F   ALTER TABLE ONLY public.room DROP CONSTRAINT room_room_hotel_id_fkey;
       public          postgres    false    217    223    3213            �           2606    17608 "   season season_season_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_season_hotel_id_fkey FOREIGN KEY (season_hotel_id) REFERENCES public.hotel(hotel_id);
 L   ALTER TABLE ONLY public.season DROP CONSTRAINT season_season_hotel_id_fkey;
       public          postgres    false    3213    221    217            4     x�}��N�0���S�DU��QGډ��P%���J�An %/�c0�҅�[�{�:�iS�~��;����p��4079Yڧ �7rUM���EZVJO��!(��D)@����4P9�|��pM%��"��*���u�'��i�`��1Ox^�\"� ���V,���$)���'�"�~�/�}��:N�w�}���؉I������z�����1�+es����?�5�����=��ܤ���Z*����
����q����������0�~ �Y�f      6   �   x�]��
�0�s�a�mӿ�(R�P=�ei�-�(��ݣ��c�T�.x��x��F'���L<�\�:2]x�����_t��m�u�B�go���x4����P	ܼ:j%9����m�Ze�f#m�^$EIV1Sy��EҔ��a�t� K��B%�תH����; �D�pZ      <   �  x�U�An$1�u�a"�M�e6Q�H�,g�?.��잨����W����?_��vQ�����狶^DQ9�JI�J~*G�ɔ�r&F�+���512�93{K����|��)��T���V���5�����
�~������^k-܁�-PK�ȏ63v�����Tr�I��!I�R��ϵ�W+��P��W+����jo���+���
#�*�\L��	̦w��vJj�n�x�
.�T��e�w�q��0	�n��bX�����"T\�,|T��b�:��=lڻ���bld�g�����Μ�6�ъ���O��A�p݃�%���!�p�c��:���ɼ��{�u�vd�b�^��[��8t����tkqIqs��n�cMp8�Z{�������),-�G��>�Q���{��hw��m!J��Δ+v�R�<���ye���bXC]�WE�:��v]��L      >   �   x�U�Aj�@E�ߧ�\FI3Y�	��C	NH���Ye��^՘�i�b����/��H��rl�.�v��]w�A����X#�K|�n}#!�*��"����۾^��p#R�B(4a�X��@�c�A��������܁��2�����k�i��٬N!���}�VoN��RD"[V"I�ܭB�"�"Z��O�0�s6�y���eb�w��2#��CUU߇gJ�      :   i   x�M��
�0C��ǈw��N��
��RP����R)!S���q=�e���+�/nI ����Lх
ɕ���I���Ga0�*g%M��D��^da�������!���'�      8   W   x�u��	�0@ѳFM�K��#�ӂ��Q!�F��]�uFI���~6#�Sfi��R��`?SR��g�i�(�s!�B2,      2   2   x�3�tt����LL��̃�\F���>�������9����pW� ��     