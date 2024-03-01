library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity body_cache is
  Port (
  given_address: in std_logic_vector (15 downto 0);
  --given_word: in std_logic_vector(7 downto 0);
  request_kind: in std_logic; -- 0 for read and 1 for write
  clk_cache: in std_logic;
  result: out std_logic_vector (7 downto 0)
--  write_to_address: out std_logic_vector (5 downto 0);
--  write_data_to_address: out std_logic_vector (7 downto 0)
  );
end body_cache;

architecture Behavioral of body_cache is

signal tag: std_logic_vector(7 downto 0); 
signal index: std_logic_vector(5 downto 0);
signal offset: std_logic_vector(1 downto 0);

signal hit_miss: std_logic := '0';

signal cs: std_logic_vector (3 downto 0) := "1111";
signal wr: std_logic_vector (3 downto 0) := "1111";

signal cs_valid: std_logic :='1';
signal wr_valid: std_logic :='1';
signal data_out_valid: std_logic_vector (7 downto 0);
signal is_valid: std_logic :='1';
signal write_through: std_logic;
signal result_compare: std_logic;

signal completed_state:std_logic :='1';
signal prepared_update: std_logic :='1';

signal rd_main_memory: std_logic :='1';
signal wr_main_memory: std_logic :='1';
signal data_to_main_memory: std_logic_vector(7 downto 0);
signal address: std_logic_vector (15 downto 0);
signal reminder: integer := 0;-- conv_integer(address) mod 4; --move these
signal start_address_block: std_logic_vector(15 downto 0);


component control_unit is
 Port (
--  given_state: in std_logic_vector(1 downto 0);
  hit_miss_cu: in std_logic; -- 0 for hit, 1 for miss
  offset: in std_logic_vector(1 downto 0);
  clk: in std_logic;
  cs_out: out std_logic_vector (3 downto 0);
  wr_out: out std_logic_vector (3 downto 0);
  cs_valid_out: out std_logic;
  wr_valid_out: out std_logic;
  
  rd_main_memory: out std_logic;
  wr_main_memory: out std_logic;
  
  next_state: out std_logic_vector(1 downto 0) );
end component;

component block64x8 is
  Port (
  a: in std_logic_vector (5 downto 0);
  din: in std_logic_vector (7 downto 0);
  dout: out std_logic_vector (7 downto 0);
  cs: in std_logic;
  wr: in std_logic );
end component;

component block64x9 is
  Port (
  a: in std_logic_vector (5 downto 0);
  din: in std_logic_vector (7 downto 0);
  dout: out std_logic_vector (7 downto 0);
  is_valid: out std_logic;
  write_through: out std_logic;
  cs: in std_logic;
  wr: in std_logic );
end component;


--
type data_out_type is array (0 to 3) of std_logic_vector(7 downto 0);
    signal data_out : data_out_type :=(
        others => x"00"
    );
--    signal data_out_main_memory: data_out_type :=(
--        others => x"00"
--    );

--type start_address_type is array (0 to 3) of std_logic_vector(7 downto 0);
--    signal value_start_address_block_plus : start_address_type :=(
--        others => x"00"
--    );
    
    

begin

address <= given_address;

--process(address)
--    variable reminder: integer := conv_integer(address) mod 4;
--begin
--    start_address_block <= address  - reminder;
--end process;

cu: control_unit port map (
--    given_state => current_state,
    hit_miss_cu => hit_miss,
    offset => offset,
    clk => clk_cache,
    cs_valid_out => cs_valid,
    wr_valid_out => wr_valid,
    cs_out =>cs,
    wr_out=> wr,
    
    --next_state => current_state,
    rd_main_memory=>rd_main_memory,
    wr_main_memory=>wr_main_memory
    
);

wordId00: block64x8 port map(
    a => index,
    din => address(7 downto 0),
    dout => data_out(0),
    cs => cs(0),
    wr => wr(0)
);

wordId01: block64x8 port map(
    a => index,
    din => address(7 downto 0),
    dout => data_out(1),
    cs => cs(1),
    wr => wr(1)
);

wordId10: block64x8 port map(
    a => index,
    din => address(7 downto 0),
    dout => data_out(2),
    cs => cs(2),
    wr => wr(2)
);

wordId11: block64x8 port map(
    a => index,
    din => address(7 downto 0),
    dout => data_out(3),
    cs => cs(3),
    wr => wr(3)
);

valid: block64x9 port map (
    a=> index,
    din => tag,
    dout => data_out_valid,
    is_valid => is_valid,
    write_through => write_through,
    cs => cs_valid,
    wr => wr_valid
);

tag <= address (15 downto 8);
index <= address(7 downto 2);
offset <= address(1 downto 0);

result_compare <= '1' when tag = data_out_valid;
hit_miss <= is_valid and result_compare; 

result <= data_out(conv_integer(offset)) when hit_miss = '1'
    else (others =>'Z'); --mux

end Behavioral;
