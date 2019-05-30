# Przykładowy format XML'a wymaganego w CommandManager

Nazwy comend, tj set oraz operate opisane w JsonCommandInput.md


```xml
<SingleCommandList>
    <command>
        <commandName>set</commandName>
        <x>0</x>
        <y>0</y>
    </command>
    <command>
        <commandName>operate</commandName>
        <x>100</x>
        <y>0</y>
    </command>
    <command>
            <commandName>operate</commandName>
            <x>100</x>
            <y>200</y>
    </command>
    <command>
            <commandName>operate</commandName>
            <x>200</x>
            <y>200</y>
    </command>
    <command>
            <commandName>operate</commandName>
            <x>0</x>
            <y>0</y>
    </command>
</SingleCommandList>
```
