//
//  CategoryViewController.swift
//  Todoey2
//
//  Created by Tung Nguyen Doan on 28/03/20.
//  Copyright © 2020 Tung Nguyen Doan. All rights reserved.
//

import UIKit
import CoreData

class CategoryViewController: UITableViewController {
    
    var categories = [Category]()
    var selected = 0
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext

    override func viewDidLoad() {
        super.viewDidLoad()
        loadData()
        
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return categories.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "categoryCell", for: indexPath)
        cell.textLabel?.text = categories[indexPath.row].name!
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        selected = indexPath.row
        performSegue(withIdentifier: "goToItems", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.destination is TodoListViewController {
            let vc = segue.destination as? TodoListViewController
            vc?.category = categories[selected]
        }
    }
    
    @IBAction func addBtnPressed(_ sender: Any) {
        let alert = UIAlertController(title: "New Category", message: nil, preferredStyle: .alert)
        let textField = UITextField()
        alert.addTextField { (textfield) in
            textField.placeholder = "Enter cew category"
        }
        let alertAction = UIAlertAction(title: "Add", style: .default) { (action) in
            let newCat = Category(context: self.context)
            newCat.name = alert.textFields![0].text
            self.categories.append(newCat)
            self.saveCategories()
        }
        
        alert.addAction(alertAction)
        present(alert, animated: true, completion: nil)
        
    }
    
    func saveCategories() {
        do {
            try context.save()
            self.tableView.reloadData()
        } catch {
            print(error)
        }
    }
    
    func loadData() {
        let request : NSFetchRequest<Category> = Category.fetchRequest()
        do {
            categories = try context.fetch(request)
            self.tableView.reloadData()
        } catch {
            print(error)
        }
    }
    
}
