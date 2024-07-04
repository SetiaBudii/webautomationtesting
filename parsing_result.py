import json

# Load the JSON file
json_file = "result/cucumber-report.json"
with open(json_file, 'r') as file:
    data = json.load(file)

# Initialize a counter for numbering the scenarios
scenario_counter = 1

# Iterate over the elements to extract required information
for feature in data:
    for element in feature.get('elements', []):
        if element.get('type') == 'scenario':
            scenario_name = element.get('name', 'Unnamed Scenario')
            print(f"- no: {scenario_counter}")
            print(f"- nama skenario: {scenario_name}")
            
            for step in element.get('steps', []):
                step_name = step.get('name', 'Unnamed Step')
                status = step.get('result', {}).get('status', 'unknown')
                error_message = step.get('result', {}).get('error_message', 'No error')
                
                print(f"  - Step: {step_name}")
                print(f"  - status: {status}")
                
                if status == "failed":
                    # Use splitlines() method to handle error_message splitting
                    first_line_error_message = error_message.splitlines()[0]
                    print(f"  - nama error: {first_line_error_message}")
                    
            scenario_counter += 1
            print("\n")
