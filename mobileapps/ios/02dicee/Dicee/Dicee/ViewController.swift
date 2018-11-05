//
//  ViewController.swift
//  Dicee
//
//  Created by Tung Nguyen Doan on 4/11/18.
//  Copyright © 2018 Tung Nguyen Doan. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var dice1: UIImageView!
    @IBOutlet weak var dice2: UIImageView!
    
    @IBAction func rollemBtn(_ sender: Any) {
        let n1 = Int.random(in: 1..<7)
        let n2 = Int.random(in: 1..<7)
        dice1.image = UIImage(named: "dice\(n1)")
        dice2.image = UIImage(named: "dice\(n2)")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


}

