class CreateReservations < ActiveRecord::Migration
  def change
    create_table :reservations do |t|
      t.string :Email_id
      t.integer :Train_id
      t.integer :PNR
      t.date :Available_date
      t.string :Reservation_status
      t.date :Reservation_date

      t.timestamps
    end
  end
end
