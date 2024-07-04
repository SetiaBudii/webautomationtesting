import json

# Load the JSON file
json_file = "result/cucumber-report.json"
output_file = "result/Test-Report.json"

with open(json_file, 'r') as file:
    data = json.load(file)

# Initialize a counter for numbering the scenarios
scenario_counter = 1

# Initialize a list to hold the formatted output
output_data = []

# Iterate over the elements to extract required information
for feature in data:
    for element in feature.get('elements', []):
        if element.get('type') == 'scenario':
            scenario_name = element.get('name', 'Unnamed Scenario')
            scenario_info = {
                "no": scenario_counter,
                "nama_skenario": scenario_name,
                "steps": []
            }
            
            for step in element.get('steps', []):
                step_name = step.get('name', 'Unnamed Step')
                status = step.get('result', {}).get('status', 'unknown')
                step_info = {
                    "step": step_name,
                    "status": status
                }
                
                if status == "failed":
                    error_message = step.get('result', {}).get('error_message', 'No error')
                    first_line_error_message = error_message.splitlines()[0]
                    step_info["nama_error"] = first_line_error_message
                
                scenario_info["steps"].append(step_info)
                
            output_data.append(scenario_info)
            scenario_counter += 1

# Write the output to a JSON file
with open(output_file, 'w') as file:
    json.dump(output_data, file, indent=4)

print(f"Formatted report saved to {output_file}")
