library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;--libraries for vectors
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity mpg is
Port ( clk : in STD_LOGIC;
       btn : in STD_LOGIC_vector (4 downto 0);   
       enable: out STD_LOGIC_vector (4 downto 0));  
end mpg;


architecture Behavioral of mpg is
    --declare signal
        --min 4 signals
    signal freq_div : std_logic_vector (15 downto 0);
    signal q1: std_logic_vector (4 downto 0);
    signal q2: std_logic_vector (4 downto 0);
    signal q3: std_logic_vector (4 downto 0);
    
    --end declaration signal
begin
    process(clk) --counter
        
    begin
        if clk = '1' and clk'event then
            --counter
            freq_div <= freq_div + 1;
        end if; --if clk
    end process;
  
    process(clk, btn)
        
    begin
        if clk = '1' and clk'event then
            if freq_div = x"1111" then
                q1 <= btn;
                      
            end if; -- freq_div if
        end if; --if clk
    end process;
    
    process(clk, q1)
    begin
        if clk = '1' and clk'event then
            q2 <= q1;
        end if;
    end process;
    
    process(clk, q2)
    begin
        if clk = '1' and clk'event then
            q3 <= q2;
        end if;
    end process;

    enable <= q2 and  not q3;
end Behavioral;
