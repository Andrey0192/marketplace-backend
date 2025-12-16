-- Users
create table if not exists users (
  id bigserial primary key,
  username varchar(50) not null,
  email varchar(255) not null,
  role varchar(20) not null,
  created_at timestamptz not null default now(),
  constraint uq_users_username unique (username),
  constraint uq_users_email unique (email),
  constraint chk_users_role check (role in ('SELLER','BUYER','ADMIN'))
);

-- Categories
create table if not exists categories (
  id bigserial primary key,
  name varchar(120) not null,
  slug varchar(160) not null,
  constraint uq_categories_slug unique (slug)
);

-- Listings
create table if not exists listings (
  id bigserial primary key,
  title varchar(200) not null,
  description text null,
  price numeric(19,2) not null,
  currency varchar(3) not null,
  status varchar(20) not null,
  category_id bigint not null,
  seller_id bigint not null,
  created_at timestamptz not null default now(),
  constraint fk_listings_category foreign key (category_id) references categories(id),
  constraint fk_listings_seller foreign key (seller_id) references users(id),
  constraint chk_listings_status check (status in ('ACTIVE','HIDDEN')),
  constraint chk_listings_currency check (char_length(currency) = 3),
  constraint chk_listings_price check (price >= 0)
);

create index if not exists idx_listings_category_id on listings(category_id);
create index if not exists idx_listings_seller_id on listings(seller_id);
create index if not exists idx_listings_status on listings(status);

-- Orders ("order" is reserved)
create table if not exists orders (
  id bigserial primary key,
  listing_id bigint not null,
  buyer_id bigint not null,
  amount numeric(19,2) not null,
  status varchar(20) not null,
  created_at timestamptz not null default now(),
  constraint fk_orders_listing foreign key (listing_id) references listings(id),
  constraint fk_orders_buyer foreign key (buyer_id) references users(id),
  constraint chk_orders_status check (status in ('NEW','PAID','CANCELLED','COMPLETED')),
  constraint chk_orders_amount check (amount >= 0)
);

create index if not exists idx_orders_buyer_id on orders(buyer_id);
create index if not exists idx_orders_status on orders(status);
