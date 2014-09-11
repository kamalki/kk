# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

<<<<<<< HEAD
ActiveRecord::Schema.define(version: 20140911111417) do
=======
ActiveRecord::Schema.define(version: 20140911110301) do
>>>>>>> ccdb43adaaa8c293697024b42f0286770b271530

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

<<<<<<< HEAD
  create_table "customers", force: true do |t|
    t.string   "PNR"
    t.string   "passenger_name"
    t.string   "Gender"
    t.string   "Age"
    t.string   "Seat_no"
    t.string   "Class"
    t.string   "Fare"
    t.string   "Source_id"
    t.string   "Destination_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "passengers", force: true do |t|
    t.string   "PNR"
    t.string   "passenger_name"
    t.string   "Gender"
    t.string   "Age"
    t.string   "Seat_no"
    t.string   "Class"
    t.string   "Fare"
    t.string   "Source_id"
    t.string   "Destination_id"
=======
  create_table "routes", force: true do |t|
    t.integer  "train_ID"
    t.integer  "stop_number"
    t.integer  "source_distace"
    t.string   "arrival_time"
    t.string   "departure_time"
>>>>>>> ccdb43adaaa8c293697024b42f0286770b271530
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "stations", force: true do |t|
    t.integer  "sation_id"
    t.string   "station_name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "trains", force: true do |t|
    t.string   "train_name"
    t.string   "train_type"
    t.string   "seats_class1"
    t.string   "seats_class2"
    t.string   "fare_class1"
    t.string   "fare_class2"
    t.string   "available_days"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "users", force: true do |t|
    t.string   "name"
    t.string   "gender"
    t.integer  "age"
    t.string   "email_id"
    t.integer  "mobile"
    t.string   "city"
    t.string   "state"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end