//
//  ViewController.swift
//  Quizzler
//
//  Created by Angela Yu on 25/08/2015.
//  Copyright (c) 2015 London App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    //Place your instance variables here
    let allQuestions = QuestionBank()
    var questionIndex: Int  = 0
    var pickedAnswer: Bool = false
    var score: Int = 0
    
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet var progressBar: UIView!
    @IBOutlet weak var progressLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let firstQuestion = allQuestions.list[0]
        questionLabel.text = firstQuestion.questionText
    }


    @IBAction func answerPressed(_ sender: AnyObject) {
        pickedAnswer = sender.tag == 1
        checkAnswer()
    }
    
    
    func updateUI() {
        
            let question = allQuestions.list[questionIndex]
            questionLabel.text = question.questionText
            progressLabel.text = "\(questionIndex + 1)/13"
            scoreLabel.text = "Score: \(score)"
            progressBar.frame.size.width = (view.frame.size.width/13) * CGFloat(questionIndex+1)
        
    }
    

    func nextQuestion() {
        if(questionIndex < allQuestions.list.count-1) {
            questionIndex += 1
        } else {
            let alert = UIAlertController(title: "Alert", message: "Quiz ends! Restart?", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: NSLocalizedString("Restart", comment: "Default action"), style: .default, handler: { _ in
                self.startOver()
            }))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    
    func checkAnswer() {
       
            if allQuestions.list[questionIndex].answer ==  pickedAnswer {
                score += 1
                
            }
            nextQuestion()
            updateUI()
    }
    
    
    func startOver() {
        questionIndex = 0
        score = 0
        updateUI()
    }
    
}
