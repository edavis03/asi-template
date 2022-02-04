create TABLE person
(
    id   serial primary key,
    name text not null,
    team_id bigint not null default 1,
    constraint fk_team
        foreign key (team_id) references Team (id)
)