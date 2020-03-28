//
//  ViewController.swift
//  Todoey2
//
//  Created by Tung Nguyen Doan on 24/03/20.
//  Copyright © 2020 Tung Nguyen Doan. All rights reserved.
//

import UIKit
import CoreData

class TodoListViewController: UITableViewController {
    
    @IBOutlet weak var searchBar: UISearchBar!
    var itemArray = [RowItem]()
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    var category: Category = Category()
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        loadItem()
        searchBar.delegate = self
//        let dataPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        print(category.name!)
        
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itemArray.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TodoItemCell", for: indexPath)
        
        cell.textLabel?.text = itemArray[indexPath.row].text
        cell.accessoryType = itemArray[indexPath.row].checked ? .checkmark : .none
        
        return cell
    }
    
    //MARK - TABleview delegate method
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
       
        tableView.deselectRow(at: indexPath, animated: true)
        
        itemArray[indexPath.row].checked = !itemArray[indexPath.row].checked
        //itemArray[indexPath.row].setValue("Done", forKey: "text")
        let item = itemArray.remove(at: indexPath.row)
        context.delete(item)
        saveItem()
        
//        if itemArray[indexPath.row].checked {
//            tableView.cellForRow(at: indexPath)?.accessoryType = .checkmark
//        } else {
//            tableView.cellForRow(at: indexPath)?.accessoryType = .none
//        }
        
    }

    @IBAction func addBtnPressed(_ sender: UIBarButtonItem) {
        print("pressed")
        let alert = UIAlertController(title: "Add New Todoey item", message: "", preferredStyle: .alert)
        
        let action = UIAlertAction(title: "Add item", style: .default) { (action) in

            let item = RowItem(context: self.context)
            item.text = alert.textFields?[0].text! ?? ""
            item.checked = false
            item.category = self.category
            
            self.itemArray.append(item)
            
            self.saveItem()
            
            
        }
        alert.addTextField { (textfield) in
            textfield.placeholder = "Create new item"
            print(textfield.text!)
        }
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }
    
    func saveItem() {
       
        do {
            try context.save()
            print("saved")
            self.tableView.reloadData()
        } catch {
            print(error)
        }
        
    }
    
    func loadItem() {
        let request: NSFetchRequest<RowItem> = RowItem.fetchRequest()
        request.predicate = NSPredicate(format: "category.name MATCHES %@", category.name!)
        
        do {
             itemArray =  try context.fetch(request)
        } catch { print(error)}
    }
    
    
    
}
//MARK: Search bar method

extension TodoListViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        let request : NSFetchRequest<RowItem> = RowItem.fetchRequest()
        request.predicate = NSPredicate(format: "(text CONTAINS[c] %@ AND category.name MATCHES[c] %@) OR %@.length == 0", searchText, category.name!, searchText)
        do {
            itemArray = try context.fetch(request)
            self.tableView.reloadData()
        } catch {
            print(error)
        }
        //print("search change", searchText)
    }
}

