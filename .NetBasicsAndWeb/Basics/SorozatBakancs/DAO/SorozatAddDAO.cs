using Microsoft.Data.Sqlite;
using SorozatBakancs.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace SorozatBakancs.DAO
{
    public class SorozatAddDAO : ISorozatDAO
    {
        private const string CONN_STRING = @"Data Source=../../../../DB/sorik.db;";
        
        public bool AddSorozat(Sorozat s)
        {
            using (SqliteConnection connection = new SqliteConnection(CONN_STRING))
            {
                connection.Open();

                SqliteCommand command = connection.CreateCommand();
                // insert into valami (oszlop 1,...) values (1,2,3,4,...)
                command.CommandText = "insert into Series "
                    + "(Title, Category, Season, Episodes, Priority) values "
                    + "(@title, @category, @season, @episodes, @priority);";

                command.Parameters.Add("title", (SqliteType)System.Data.DbType.String).Value = s.title;
                command.Parameters.Add("category", (SqliteType)System.Data.DbType.String).Value = s.category;
                command.Parameters.Add("season", (SqliteType)System.Data.DbType.Int32).Value = s.season;
                command.Parameters.Add("episodes", (SqliteType)System.Data.DbType.Int32).Value = s.episodes;
                command.Parameters.Add("priority", (SqliteType)System.Data.DbType.Int32).Value = s.priority;

                bool ret = true;
                try
                {
                    command.ExecuteNonQuery();
                }
                catch (Exception e)
                {
                    ret = false;
                }

                connection.Close();
                return ret;
            }
        }

        public Sorozat GetSorozat(int s)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Sorozat> GetSorozatok()
        {
            List<Sorozat> sorik = new List<Sorozat>();

            using (SqliteConnection connection = new SqliteConnection(CONN_STRING))
            {
                connection.Open();

                SqliteCommand command = connection.CreateCommand();
                command.CommandText = "select * from Series";

                using var reader = command.ExecuteReader(); // SQLiteDataReader

                while (reader.Read())
                {
                    sorik.Add(new Sorozat
                    {
                        id = reader.GetInt32(reader.GetOrdinal("ID")),
                        title = reader.GetString(reader.GetOrdinal("Title")),
                        category = reader.GetString(reader.GetOrdinal("Category")),
                        season = reader.GetInt32(reader.GetOrdinal("Season")),
                        episodes = reader.GetInt32(reader.GetOrdinal("Episodes")),
                        priority = reader.GetInt32(reader.GetOrdinal("Priority"))
                    });
                }

                sorik.Sort(delegate (Sorozat c1, Sorozat c2) { return c2.priority.CompareTo(c1.priority); });
                connection.Close();
                return sorik;
            }
        }

        public bool ModifySorozat(Sorozat s)
        {
            using SqliteConnection connection = new SqliteConnection(CONN_STRING);
            connection.Open();

            SqliteCommand command = connection.CreateCommand();
            // update Heroes SET ... where ID=@id
            command.CommandText = "update Series set "
                + "Title=@title, Category=@category, Season=@season, Episodes=@episodes, Priority = @priority "
                + "where ID=@id;";

            command.Parameters.Add("title", (SqliteType)System.Data.DbType.String).Value = s.title;
            command.Parameters.Add("category", (SqliteType)System.Data.DbType.String).Value = s.category;
            command.Parameters.Add("season", (SqliteType)System.Data.DbType.String).Value = s.season;
            command.Parameters.Add("episodes", (SqliteType)System.Data.DbType.Int32).Value = s.episodes;
            command.Parameters.Add("priority", (SqliteType)System.Data.DbType.Int32).Value = s.priority;
            command.Parameters.Add("id", (SqliteType) System.Data.DbType.Int32).Value = s.id;

            bool ret = true;
            try
            {
                command.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                ret = false;
            }

            connection.Close();
            return ret;
        }

        public int SorozatCount()
        {
            throw new NotImplementedException();
        }
    }
}
