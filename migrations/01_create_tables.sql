create table if not exists users
(
    id           serial primary key not null,
    name         varchar(255)       not null,
    email        varchar(255)       not null unique check (email ~* '^.+@.+\..+$'),
    password     varchar(255)       not null,
    created_at   timestamp          not null default now(),
    updated_at   timestamp          not null default now(),
    deleted_at   timestamp,
    last_read_at timestamp          not null default now(),
    role         integer            not null default 3, -- 1: admin, 2: formatteur, 3: apprenant
    banned       boolean            not null default false,
    image        varchar(255)
);



create table if not exists promotions
(
    id         serial primary key not null,
    name       varchar(255)       not null,
    year       integer            not null default 2019,
    created_at timestamp          not null default now(),
    updated_at timestamp          not null default now(),
    deleted_at timestamp,
    user_id    integer references users (id) on delete set null on update cascade -- formatteur
);


--  join table between users and promotions
create table if not exists users_promotions
(
    user_id      integer references users (id) on delete cascade on update cascade,
    promotion_id integer references promotions (id) on delete cascade on update cascade,
    created_at   timestamp not null default now(),
    updated_at   timestamp not null default now(),
    deleted_at   timestamp,
    primary key (user_id, promotion_id)
);


create table if not exists briefs
(
    id                   serial primary key not null,
    name                 varchar(255)       not null,
    created_at           timestamp          not null default now(),
    updated_at           timestamp          not null default now(),
    deleted_at           timestamp,
    description          text,
    context              text,
    language             int                not null default 1,                                  -- 1: fr, 2: en
    image                varchar(255),
    technologies         text[],
    frameworks           text[],
    deliverables         text[],
    assessment_methods   text[],
    learning_methods     text[],
    performance_criteria text,
    deadline             timestamp,
    skills               jsonb,
    user_id              integer references users (id) on delete set null on update cascade,     -- formatteur
    promotion_id         integer references promotions (id) on delete set null on update cascade -- promotion
);

create table if not exists briefs_messages
(
    id         serial primary key not null,
    data       jsonb              not null default '{}'::jsonb,
    created_at timestamp          not null default now(),
    updated_at timestamp          not null default now(),
    deleted_at timestamp,
    user_id    integer references users (id) on delete set null on update cascade,
    brief_id   integer references briefs (id) on delete set null on update cascade
);


