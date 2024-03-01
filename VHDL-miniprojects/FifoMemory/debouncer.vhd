library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Debouncer is
    Port (
        clk : in STD_LOGIC;
        button : in STD_LOGIC;
        reset : in STD_LOGIC;
        debounced_button : out STD_LOGIC
    );
end Debouncer;

architecture Behavioral of Debouncer is
    signal debounced_state : STD_LOGIC := '0';
    signal prev_button_state : STD_LOGIC := '0';

begin
    process(clk, reset)
    begin
        if reset = '1' then
            debounced_state <= '0';
        elsif rising_edge(clk) then
            prev_button_state <= button;
            if button = '1' and prev_button_state = '0' then
                debounced_state <= '1';
            else
                debounced_state <= '0';
            end if;
        end if;
    end process;

    debounced_button <= debounced_state;

end Behavioral;
