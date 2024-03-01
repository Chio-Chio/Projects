library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity control_unit is
  Port (
  --given_state: in std_logic_vector(1 downto 0);
  hit_miss_cu: in std_logic; -- 0 for hit, 1 for miss
  --clk cycle
--  cs_in: in std_logic_vector (3 downto 0);
--  wr_in: in std_logic_vector (3 downto 0);
--  cs_valid_in: in std_logic;
--  wr_valid_in: in std_logic;  
  offset: in std_logic_vector(1 downto 0);
  
  clk: in std_logic;
  
  cs_out: out std_logic_vector (3 downto 0);
  wr_out: out std_logic_vector (3 downto 0);
  cs_valid_out: out std_logic;
  wr_valid_out: out std_logic;
  
  rd_main_memory: out std_logic; --not in use
  wr_main_memory: out std_logic;
  
  next_state: out std_logic_vector(1 downto 0) ); -- not in use
  
  
end control_unit;

architecture Behavioral of control_unit is
--signal state: std_logic_vector(2 downto 0):= "00";
    -- 00 for search,  first thing after the address is given
    -- 01 for update, after search is done
                      -- found address, hit => rd/wr
                      --               miss => update => rd/wr
    -- 10 for rd/wr
signal state: std_logic_vector(1 downto 0 ):="00";
signal nxt_state: std_logic_vector(1 downto 0):="00";

begin

process(clk)
begin
    if rising_edge(clk) then
        cs_out <= "1111";
        wr_out <= "1111";
        cs_valid_out <= '1';
        wr_valid_out <= '1' ;
        rd_main_memory <='1';
        wr_main_memory <='1';
            case state is
            when "00" => --search
                cs_valid_out <= '0'; --read
                wr_valid_out <= '1';
                if hit_miss_cu = '1' then
                    state <= "10";
                else
                    state <= "01"; --not hit
                end if;
            when "01" => -- update
                rd_main_memory <='0'; -- read from main memory
                cs_valid_out <= '0'; --write in tag
                wr_valid_out <= '0';
                cs_out <= "0000"; --write to all units
                wr_out <= "0000";
                state <= "10";
            when "10" => -- rd/wr
                state <= "00";
                cs_out(conv_integer(offset)) <= '0'; -- DEMUX
                wr_main_memory <='0';
            when others => 
                state <= "00";
        end case;
 
    end if;   
end process;

end Behavioral;
