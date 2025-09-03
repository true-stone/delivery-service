INSERT INTO "user"(username, password, name, created_at, updated_at)
values ('username1', '$2a$10$MfnorGDmG8BhGs.vh5/xEuDEJkD0cq6d9NUTyL0nRDp2zuvDI1UWm', '홍길동', NOW(), NOW());

-- day 0
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DISPATCHING', '서울시 픽업지 0-A', '서울시 목적지 0-A', 1,
        DATEADD('MINUTE', -60, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -0, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -0, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -0, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DISPATCHING', '서울시 픽업지 0-B', '서울시 목적지 0-B', 2,
        DATEADD('MINUTE', -50, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -0, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -0, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -0, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 0-C', '서울시 목적지 0-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -0, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -0, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -0, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -0, NOW())), 0);

-- day 1
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 1-A', '서울시 목적지 1-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -1, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -1, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -1, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 1-B', '서울시 목적지 1-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -1, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -1, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -1, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 1-C', '서울시 목적지 1-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -1, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -1, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -1, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -1, NOW())), 0);

-- day 2
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 2-A', '서울시 목적지 2-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -2, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -2, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -2, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 2-B', '서울시 목적지 2-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -2, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -2, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -2, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 2-C', '서울시 목적지 2-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -2, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -2, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -2, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -2, NOW())), 0);

-- day 3
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 3-A', '서울시 목적지 3-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -3, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -3, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -3, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 3-B', '서울시 목적지 3-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -3, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -3, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -3, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 3-C', '서울시 목적지 3-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -3, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -3, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -3, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -3, NOW())), 0);

-- day 4
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 4-A', '서울시 목적지 4-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -4, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -4, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -4, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 4-B', '서울시 목적지 4-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -4, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -4, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -4, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 4-C', '서울시 목적지 4-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -4, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -4, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -4, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -4, NOW())), 0);

-- day 5
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 5-A', '서울시 목적지 5-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -5, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -5, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -5, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 5-B', '서울시 목적지 5-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -5, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -5, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -5, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 5-C', '서울시 목적지 5-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -5, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -5, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -5, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -5, NOW())), 0);

-- day 6
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 6-A', '서울시 목적지 6-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -6, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -6, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -6, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 6-B', '서울시 목적지 6-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -6, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -6, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -6, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 6-C', '서울시 목적지 6-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -6, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -6, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -6, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -6, NOW())), 0);

-- day 7
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 7-A', '서울시 목적지 7-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -7, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -7, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -7, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 7-B', '서울시 목적지 7-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -7, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -7, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -7, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 7-C', '서울시 목적지 7-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -7, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -7, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -7, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -7, NOW())), 0);

-- day 8
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 8-A', '서울시 목적지 8-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -8, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -8, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -8, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 8-B', '서울시 목적지 8-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -8, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -8, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -8, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 8-C', '서울시 목적지 8-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -8, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -8, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -8, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -8, NOW())), 0);

-- day 9
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 9-A', '서울시 목적지 9-A', 0,
        DATEADD('MINUTE', -60, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', -45, DATEADD('DAY', -9, NOW())),
        DATEADD('MINUTE', -20, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -9, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -60, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 0, DATEADD('DAY', -9, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 9-B', '서울시 목적지 9-B', 0,
        DATEADD('MINUTE', -50, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', -35, DATEADD('DAY', -9, NOW())),
        DATEADD('MINUTE', -10, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -9, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -50, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 10, DATEADD('DAY', -9, NOW())), 0);
INSERT INTO delivery_order (order_uid, status, pickup_address, dest_address, change_count, paid_at, accepted_at,
                            picked_up_at, delivered_at, user_id, created_at, updated_at, version)
VALUES (CAST(RANDOM_UUID() AS VARCHAR(36)), 'DELIVERED', '서울시 픽업지 9-C', '서울시 목적지 9-C', 0,
        DATEADD('MINUTE', -40, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', -25, DATEADD('DAY', -9, NOW())),
        DATEADD('MINUTE', 0, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -9, NOW())),
        (SELECT id FROM "user" WHERE username = 'username1'),
        DATEADD('MINUTE', -40, DATEADD('DAY', -9, NOW())), DATEADD('MINUTE', 20, DATEADD('DAY', -9, NOW())), 0);