library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all;

entity control_unit is
  Port (
  instruction: in std_logic_vector(31 downto 0);
  clk: in std_logic;
  rst : in STD_LOGIC;
   MemRead : out STD_LOGIC;
   MemWrite : out STD_LOGIC;
   RegDst : out STD_LOGIC;
   RegWrite : out STD_LOGIC;
   AluSrcA : out STD_LOGIC;
   AluSrcB : out STD_LOGIC_VECTOR (1 downto 0);
   MemtoReg : out STD_LOGIC;
   IRWrite : out STD_LOGIC;
   PCWrite : out STD_LOGIC;
   AluOp : out STD_LOGIC_VECTOR (1 downto 0)
   );
end control_unit; 

architecture Behavioral of control_unit is
    signal counter: std_logic_vector(2 downto 0) :="000";
begin
    process (clk)
    begin
        if rising_edge(clk) then
            if rst = '1' then
                 counter <= "000";
            else
                if(counter = "100") then
                    counter <= "000";
                else
                    counter <= counter + 1;
                end if;
            end if;
        end if;
    end process;

    process(counter)
    begin
       MemRead <= '0';
       MemWrite <= '0';
       RegDst <= '0';
       RegWrite <= '0';
       AluSrcA <= '0';
       AluSrcB <= "00";
       MemtoReg <= '0';
       IRWrite <= '0';
       PCWrite <= '0';
       AluOp <= "00";
    
        if counter = "000" then
            -- fetch
            PCWrite <='1'; -- pc inc
            IRWrite <='1';
            AluSrcB <= "00"; -- increment pc with 4
        elsif counter = "001" then
            -- decode
        elsif counter = "010" then
            -- execution
            -- r type
            if instruction(31 downto 26) = "000000" then
                if instruction(5 downto 0) = "100000" then -- modified
                    aluop <="00";
                elsif instruction(5 downto 0) = "100010" then -- modified
                    aluop <="01";
                elsif instruction(5 downto 0) = "100100" then -- modified
                    aluop <="10"; 
                elsif instruction(5 downto 0) = "100101" then -- modified
                    aluop <="11";    
                end if;
                AluSrcB <= "01";
            elsif ( instruction(31 downto 26) = "100011" or 
                  instruction(31 downto 26) = "101011" ) then
                  -- load or store
                  aluop <="00";
                  AluSrcA <= '1';
                  AluSrcB <= "10";
                  
            end if;
            
        elsif counter = "011" then
            --memory
            if instruction(31 downto 26) = "000000" then
                irWrite <= '1';
            elsif instruction(31 downto 26) = "100011" then
                -- load
                MemRead <= '1';
            elsif instruction(31 downto 26) = "101011" then
                  -- store
                 MemWrite <= '1';    
            end if;
            
        elsif counter = "100" then
            -- writeback
            if instruction(31 downto 26) = "100011" then
                -- load
               MemtoReg <= '1';
            end if;
        end if;
    end process;
end Behavioral;
